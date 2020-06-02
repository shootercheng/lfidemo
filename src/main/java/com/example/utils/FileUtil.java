package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @author James
 */
public class FileUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static final int BUFFER_SIZE = 1024;

    /**
     * 1024 256, 会报错
     * java.lang.ClassFormatError: Extra bytes at the end of class file pkg/HelloWorld
     * 读取字节数1字节最好
     */
    public static final int CLASS_BUFF_SIZE = 1;

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

    public static void findBigSizeFiles(String filePath, List<File> files, long maxFileSize) {
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
                findBigSizeFiles(curPath, files, maxFileSize);
            }
        }
    }


    /**
     *
     * @param inputFilePath
     * @param outPath
     * @param partSize
     * @throws IOException
     */
    public static void splitFile(String inputFilePath, String outPath, int partSize) throws IOException {
        File file = new File(inputFilePath);
        if (!file.exists() || !file.isFile()) {
            LOGGER.error("file not exists {}", inputFilePath);
            return;
        }
        File targetFile = new File(outPath);
        if (!targetFile.isDirectory()) {
            LOGGER.error("out path is not directory {}", outPath);
            return;
        }
        InputStream in = new FileInputStream(file);
        String fileName = file.getName().trim();
        byte[] buffer = new byte[partSize];
        int bytesRead = -1;
        int part = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            String targetFilePath = outPath + File.separator + part + " " + fileName;
            try (OutputStream out = new FileOutputStream(targetFilePath)) {
                out.write(buffer, 0, bytesRead);
                out.flush();
            }
            part++;
        }
    }

    public static void mergeFile(String inputPath, String outputFilePath, int partSize) throws IOException {
        File file = new File(inputPath);
        if (!file.isDirectory()) {
            LOGGER.error("input path is not directory {}", inputPath);
            return;
        }
        outputFilePath = outputFilePath + "_keep";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(outputFilePath, "rw")) {
            File[] files = file.listFiles();
            for (File partFile : files) {
                String name = partFile.getName();
                String[] nameArr = name.split(" ");
                int index = Integer.valueOf(nameArr[0]);
                try (InputStream inputStream = new FileInputStream(partFile)) {
                    int length = inputStream.available();
                    byte[] bytes = new byte[length];
                    inputStream.read(bytes);
                    // 计算偏移量
                    long offset = index * partSize;
                    randomAccessFile.seek(offset);
                    randomAccessFile.write(bytes);
                }
            }
        }
        renameFile(outputFilePath);
    }

    public static void mergeNioFile(String inputPath, String outputFilePath, int partSize) throws IOException {
        File file = new File(inputPath);
        if (!file.isDirectory()) {
            return;
        }
        outputFilePath = outputFilePath + "_keep";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(outputFilePath, "rw");
             FileChannel fileChannel = randomAccessFile.getChannel()) {
            File[] files = file.listFiles();
            for (File partFile : files) {
                String name = partFile.getName();
                String[] nameArr = name.split(" ");
                int index = Integer.valueOf(nameArr[0]) - 1;
                String curFilePath = partFile.getAbsolutePath();
                try (InputStream inputStream = new FileInputStream(curFilePath)) {
                    int length = inputStream.available();
                    byte[] bytes = new byte[length];
                    inputStream.read(bytes);
                    // 计算偏移量
                    long offset = index * partSize;
                    MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, offset, bytes.length);
                    mappedByteBuffer.put(bytes);
                    mappedByteBuffer.clear();
                }
            }
        }
        // 文件重命名
        renameFile(outputFilePath);
    }

    private static boolean renameFile(String outputFilePath) {
        // 文件重命名
        String orgFileName = outputFilePath.substring(0, outputFilePath.length() - "_keep".length());
        File keepFile = new File(outputFilePath);
        if (!keepFile.isFile()) {
            LOGGER.info("output path is not file {}", outputFilePath);
            return false;
        }
        File newFile = new File(orgFileName);
        if (newFile.exists()) {
            LOGGER.info("delete exists file {}", orgFileName);
            newFile.delete();
        }
        return keepFile.renameTo(newFile);
    }

    public static byte[] readFileByte(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        int size = inputStream.available();
        byte[] bytes = new byte[size];
        inputStream.read(bytes);
        return bytes;
    }

    public static void writeBytesToFile(byte[] bytes, String filePath, int bufferSize) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(bytes);
             OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[bufferSize];
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer, 0, bufferSize);
                outputStream.flush();
            }
        }
    }
}
