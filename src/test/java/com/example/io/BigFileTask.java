package com.example.io;

import com.example.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author James
 */
public class BigFileTask implements Callable<List<File>> {

    private List<String> filePaths;

    private Long maxFileSize;

    public BigFileTask(List<String> filePaths, Long maxFileSize) {
        this.filePaths = filePaths;
        this.maxFileSize = maxFileSize;
    }

    @Override
    public List<File> call() {
        List<File> result = new ArrayList<>(10000);
        for (String filePath : filePaths) {
            FileUtil.findBigSizeFiles(filePath, result, maxFileSize);
        }
        return result;
    }
}
