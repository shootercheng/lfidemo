package com.example.io;

import com.example.utils.FileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author James
 */
public class FileTest {
    private static final int MAX_FILE_SIZE = 500 * 1024 * 1024;

    @Test
    public void testFileSize() {
        String filePath = "D:/jasypt-dev-config.png";
        File file = new File(filePath);
        long size = file.length();
        double fkb = size / 1024D;
        System.out.println(file.getAbsolutePath() + " " + fkb);
    }

    @Test
    public void testFindFiles() {
        String filePath = "D:/Data/conet";
        List<File> fileList = new ArrayList<>();
        FileUtil.findFiles(filePath, fileList);
        for (File file : fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }

    @Test
    public void testBigFile() {
        long startTime = System.currentTimeMillis();
        String filePath = "D:/";
        List<File> fileList = new ArrayList<>();
        FileUtil.findFiles(filePath, fileList);
        for (File file : fileList) {
            long fileSize = file.length();
            if (fileSize > MAX_FILE_SIZE) {
                System.out.println(file.getAbsolutePath() + "_" + fileSize);
            }
        }
        System.out.println("time : " + (System.currentTimeMillis() - startTime));
    }

    public List<String> findOneLevelPath(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.isFile()) {
            return new ArrayList<>();
        }
        List<String> resultList = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return resultList;
            }
            for (File f : files) {
                if (f == null) {
                    continue;
                }
                resultList.add(f.getAbsolutePath());
            }
        }
        return resultList;
    }

    @Test
    public void multiThreadTest() throws IOException {
        long startTime = System.currentTimeMillis();
        int threadNum = 4;
        // 开始目录
        String filePath = "D:/";
        List<String> filePaths = findOneLevelPath(filePath);
        int filePathSize = filePaths.size();
        // 每个线程查找文件数据量
        int filePathNum;
        if (filePathSize % threadNum == 0) {
            filePathNum = filePathSize / threadNum;
        } else {
            filePathNum = filePathSize / threadNum + 1;
        }
        List<BigFileTask> bigFileTasks = new ArrayList<>();
        List<String> threadFilePath = new ArrayList<>();
        for (String fpath : filePaths) {
            threadFilePath.add(fpath);
            if (threadFilePath.size() == filePathNum) {
                List<String> threadPathParam = new ArrayList<>(threadFilePath);
                BigFileTask bigFileTask = new BigFileTask(threadPathParam);
                bigFileTasks.add(bigFileTask);
                threadFilePath.clear();
            }
        }
        if (threadFilePath.size() > 0) {
            List<String> threadPathParam = new ArrayList<>(threadFilePath);
            BigFileTask bigFileTask = new BigFileTask(threadPathParam);
            bigFileTasks.add(bigFileTask);
        }
        Assert.assertEquals(threadNum, bigFileTasks.size());
        // 开始启动线程任务
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        List<Future<List<File>>> resultList = new ArrayList<>();
        for (BigFileTask bigFileTask : bigFileTasks) {
            Future<List<File>> listFuture = threadPool.submit(bigFileTask);
            resultList.add(listFuture);
        }
        // 写入任务结果
        String resultPath = "file/sizefile.txt";
        writeRuslt(resultList, resultPath);
        System.out.println("time " + (System.currentTimeMillis() - startTime));
    }

    private void writeRuslt(List<Future<List<File>>> resultList, String resultPath) {
        List<List<File>> result = new ArrayList<>(resultList.size());
        for (Future<List<File>> future : resultList) {
            try {
                List<File> fileList = future.get();
                result.add(fileList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("start write info ...........");
        try (BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(
                new FileOutputStream(resultPath, false), Charset.forName("GBK")))) {
            int i = 1;
            for (List<File> files : result) {
                bufferedWriter.write("thread :" + (i++) + "\n");
                for (File f : files) {
                    long fileSize = f.length();
                    if (f.length() > MAX_FILE_SIZE) {
                        bufferedWriter.write(f.getAbsolutePath() + "  " + fileSize + "\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
