package com.example.stream;

import com.example.utils.FileUtil;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * @author James
 */
public class StreamTest {

    @Test
    public void testReadInputStream() {
        OutputStream outputStream = new ByteArrayOutputStream();
        String filePath = "file/1.txt";
        byte[] buffer = new byte[1];
        try (InputStream inputStream = new FileInputStream(filePath)) {
            int readLen;
            while((readLen = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readLen);
            }
            System.out.println(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
