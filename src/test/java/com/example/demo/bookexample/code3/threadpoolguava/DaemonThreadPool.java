package com.example.demo.bookexample.code3.threadpoolguava;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chengdu
 * @date 2019/8/22.
 */
public class DaemonThreadPool {

    public static void testDaemonThreadPool(){
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        MoreExecutors.getExitingExecutorService((ThreadPoolExecutor) threadPool);
        threadPool.execute(() -> {
            System.out.println("Thread Name " + Thread.currentThread().getName());
        });
    }

    public static void main(String[] args){
//        testDaemonThreadPool();
        DirectExecutorTest.testFixedPool();
    }
}
