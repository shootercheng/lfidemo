package com.example.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @author James
 */
public class FileUtil {
    public static final int BUFFER_SIZE = 1024;

    public static void findFiles(String filePath, List<File> files) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            files.add(file);
            return;
        }
        if (file.isDirectory()) {
            File[] fileArr = file.listFiles();
            if (fileArr == null || fileArr.length == 0) {
//                System.out.println(file);
                return;
            }
            for (File curFile : fileArr) {
                String curPath = curFile.getAbsolutePath();
                findFiles(curPath, files);
            }
        }
    }

    /**
     * Copy the contents of the given InputStream to the given OutputStream.
     * Leaves both streams open when done.
     * @param in the InputStream to copy from
     * @param out the OutputStream to copy to
     * @return the number of bytes copied
     * @throws IOException in case of I/O errors
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        Assert.notNull(in, "No InputStream specified");
        Assert.notNull(out, "No OutputStream specified");

        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }

    public static void copy(String sourcePath, String targetPath) throws IOException {
        try (InputStream inputStream = new FileInputStream(sourcePath);
             OutputStream outputStream = new FileOutputStream(targetPath)) {
            copy(inputStream, outputStream);
        }
    }

    public static void copyNio(String sourcePath, String targetPath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(sourcePath);
            FileOutputStream fileOutputStream = new FileOutputStream(targetPath)) {
            FileChannel inputChannel = fileInputStream.getChannel();
            FileChannel outputChannel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            while ((inputChannel.read(byteBuffer)) !=-1 ) {
                byteBuffer.flip();
                outputChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        }
    }

    public static void findMaxSizeFiles(String filePath, List<File> files, long maxFileSize) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            long fileSize = file.length();
            if (fileSize > maxFileSize) {
                files.add(file);
            }
            return;
        }
        if (file.isDirectory()) {
            File[] fileArr = file.listFiles();
            if (fileArr == null || fileArr.length == 0) {
//                System.out.println(file);
                return;
            }
            for (File curFile : fileArr) {
                String curPath = curFile.getAbsolutePath();
                findMaxSizeFiles(curPath, files, maxFileSize);
            }
        }
    }
}
