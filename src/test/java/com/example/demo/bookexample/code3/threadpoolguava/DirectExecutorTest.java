package com.example.demo.bookexample.code3.threadpoolguava;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengdu
 * @date 2019/8/22.
 */
public class DirectExecutorTest {

    public static void testDirect(){
        Executor directPool = MoreExecutors.directExecutor();
        directPool.execute(() -> {
            System.out.println("Thread Name " + Thread.currentThread().getName());
        });
    }

    public static void testFixedPool(){
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.execute(() -> {
            System.out.println("Thread Name " + Thread.currentThread().getName());
        });
    }

    public static void main(String[] args){
//        testDirect();
        testFixedPool();
    }
}
