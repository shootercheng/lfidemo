package com.example.csv;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * @author chengdu
 * @date 2020/2/20
 */
public class BufferWriterTest {

    @Test
    public void testBufferExt() throws IOException {
        String filePath = "file/export/t1.csv";
        BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(
                new FileOutputStream(filePath, true), Charset.forName("GBK")));
        for (int i = 0; i < 100; i++) {
            bufferedWriter.append("testbuffer");
            bufferedWriter.newLine();
            if (i == 50) {
                throw new RuntimeException("test buffer");
            }
        }
        bufferedWriter.close();
    }

    @Test
    public void testBufferExtAuto() throws IOException {
        String filePath = "file/export/t2.csv";
        BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(
                new FileOutputStream(filePath, true), Charset.forName("GBK")));
        for (int i = 0; i < 1000000000; i++) {
            bufferedWriter.append("testbuffer");
            bufferedWriter.newLine();
            if (i == 50000000) {
                throw new RuntimeException("test buffer");
            }
        }
        bufferedWriter.close();
    }

    @Test
    public void testBufferExtData() throws IOException {
        String filePath = "file/export/t.csv";
        BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(
                new FileOutputStream(filePath, true), Charset.forName("GBK")));
        for (int i = 0; i < 100; i++) {
            bufferedWriter.append(String.valueOf(i));
            bufferedWriter.newLine();
            if (i == 50) {
                bufferedWriter.flush();
                throw new RuntimeException("test buffer");
            }
        }
        bufferedWriter.close();
    }
}
