package com.example.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author James
 */
public class ByteBufferTest {

    @Test
    public void testCreateBuffer() {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(15);
        Assert.assertEquals(0, byteBuffer1.position());
        Assert.assertEquals(15, byteBuffer1.limit());
        Assert.assertEquals(15, byteBuffer1.capacity());
        byte[] byteArr = new byte[15];
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(byteArr);
        Assert.assertEquals(0, byteBuffer2.position());
        Assert.assertEquals(15, byteBuffer2.limit());
        Assert.assertEquals(15, byteBuffer2.capacity());
    }

    @Test
    public void testBufferFlip() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        byteBuffer.put((byte)1);
        Assert.assertEquals(1, byteBuffer.position());
        byteBuffer.put((byte)2);
        Assert.assertEquals(2, byteBuffer.position());
        byteBuffer.flip();
        Assert.assertEquals(2, byteBuffer.limit());
        Assert.assertEquals(0, byteBuffer.position());
    }

    @Test
    public void testBufferClear() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)2);
        byteBuffer.clear();
        Assert.assertEquals(0, byteBuffer.position());
        Assert.assertEquals(15, byteBuffer.limit());
    }

    @Test
    public void testBufferMark() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        for (int i = 0; i < 10; i++) {
            byteBuffer.put((byte) i);
        }
        // 设置 position 位置，为读取作准备
        byteBuffer.flip();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            byte readByte = byteBuffer.get();
            System.out.print(readByte + " ");
            if (i == 4) {
                // 标记
                byteBuffer.mark();
            }
        }
        byteBuffer.reset();
        System.out.println();
        System.out.println("byte buffer reset to mark .....");
        while (byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.get() + " ");
        }
    }


    @Test
    public void testDuplicate() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        for (int i = 0; i < 10; i++) {
            byteBuffer.put((byte) i);
        }
        ByteBuffer copyByte = byteBuffer.duplicate();
        Assert.assertEquals(10,  byteBuffer.position());
        Assert.assertEquals(10,  copyByte.position());
        byteBuffer.flip();
        byteBuffer.put((byte) 10);
        Assert.assertEquals(1,  byteBuffer.position());
        Assert.assertEquals(10,  copyByte.position());
        Assert.assertEquals(byteBuffer.get(0), copyByte.get(0));
    }

    @Test
    public void testMappedByteBuffer() {
        String filePath = "D:/movie.log";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw")) {
            FileChannel fileChannel = randomAccessFile.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 100);
            byte[] bytes = new byte[100];
            int i = 0;
            while (mappedByteBuffer.hasRemaining()) {
                bytes[i] = mappedByteBuffer.get();
                i++;
            }
            String readStr = new String(bytes);
            System.out.println(readStr);
            mappedByteBuffer.put(1, (byte) 100);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBufferReadWrite() {
        String filePath = "file/1.txt";
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("代码".getBytes(StandardCharsets.UTF_8));
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("code".getBytes());
        int byteBuffer1Len = byteBuffer1.limit();
        int byteBuffer2Len = byteBuffer2.limit();
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer1, byteBuffer2};
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            FileChannel fileChannel = fileOutputStream.getChannel();
            fileChannel.write(byteBuffers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteBuffer byteBufferR1 = ByteBuffer.allocate(byteBuffer1Len);
        ByteBuffer byteBufferR2 = ByteBuffer.allocate(byteBuffer2Len);
        ByteBuffer[] byteBufferRead = new ByteBuffer[]{byteBufferR1, byteBufferR2};
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            fileChannel.read(byteBufferRead);
            String reader1 = new String(byteBufferRead[0].array(), StandardCharsets.UTF_8);
            String reader2 = new String(byteBufferRead[1].array(), StandardCharsets.UTF_8);
            Assert.assertEquals("代码", reader1);
            Assert.assertEquals("code", reader2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
