package com.example.demo.bookexample.code3.threadpool.scheduled;

import com.example.demo.bookexample.code3.threadpool.thread.TestThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chengdu
 * @date 2019/8/14.
 */
public class ScheduledPoolTest {

    public static void main(String[] args){
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
        TestThread testThread = new TestThread("test thread");
        scheduledPool.execute(testThread);
        scheduledPool.scheduleAtFixedRate(testThread, 1000,500, TimeUnit.MILLISECONDS);
//        scheduledPool.scheduleWithFixedDelay(testThread, 1000,500, TimeUnit.MILLISECONDS);
    }
}
