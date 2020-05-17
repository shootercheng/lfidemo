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

    public BigFileTask(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    @Override
    public List<File> call() throws Exception {
        List<File> result = new ArrayList<>(10000);
        for (String filePath : filePaths) {
            FileUtil.findFiles(filePath, result);
        }
        return result;
    }
}
