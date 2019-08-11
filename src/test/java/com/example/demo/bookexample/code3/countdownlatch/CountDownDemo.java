package com.example.demo.bookexample.code3.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class CountDownDemo {

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 20;
        // 所有线程共用一个 countDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);
        for (int i=0; i < threadNum; i++){
            CountDownThread countDownThread = new CountDownThread(countDownLatch, "cd thread "+ i);
            threadPool.execute(countDownThread);
        }
        countDownLatch.await();
        System.out.println("child thread over");
        threadPool.shutdown();
    }
}
