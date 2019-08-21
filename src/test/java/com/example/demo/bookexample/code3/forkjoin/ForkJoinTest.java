package com.example.demo.bookexample.code3.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author chengdu
 * @date 2019/8/21.
 */
public class ForkJoinTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 100);
        // 设置阈值
//        CountTask.setTHRESHOLD(20);
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(task);
        Long res = forkJoinTask.get();
        System.out.println(res);
    }
}
