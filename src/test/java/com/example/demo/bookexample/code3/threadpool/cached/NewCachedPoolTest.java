package com.example.demo.bookexample.code3.threadpool.cached;

import com.example.demo.bookexample.code3.threadpool.thread.TestThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengdu
 * @date 2019/8/14.
 */
public class NewCachedPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++){
            TestThread testThread = new TestThread("thread " + i);
            cachedThreadPool.execute(testThread);
//            Thread.sleep(1050);
        }
        cachedThreadPool.shutdown();
    }
}
