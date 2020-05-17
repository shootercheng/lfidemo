package com.example.io;

import com.example.utils.FileUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * @author James
 */
public class CopyFileTest {

    @Test
    public void testNioCopy() throws IOException {
        String sourcePath= "E:/BaiduNetdiskDownload/《码出高效：Java开发手册》.pdf";
        long startTime = System.currentTimeMillis();
        String targetNioPath = "E:/BaiduNetdiskDownload/Java-copy-nio-4.pdf";
        FileUtil.copyNio(sourcePath, targetNioPath);
        System.out.println("nio time " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testCopy() throws IOException {
        long startTime = System.currentTimeMillis();
        String sourcePath= "E:/BaiduNetdiskDownload/《码出高效：Java开发手册》.pdf";
        String targetPath = "E:/BaiduNetdiskDownload/Java-copy.pdf";
        FileUtil.copy(sourcePath, targetPath);
        System.out.println("time " + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        String targetNioPath = "E:/BaiduNetdiskDownload/Java-copy-nio.pdf";
        FileUtil.copyNio(sourcePath, targetNioPath);
        System.out.println("nio time " + (System.currentTimeMillis() - startTime));
    }
}
