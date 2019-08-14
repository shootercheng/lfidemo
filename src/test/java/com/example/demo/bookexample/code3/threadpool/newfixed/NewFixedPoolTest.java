package com.example.demo.bookexample.code3.threadpool.newfixed;

import com.example.demo.bookexample.code3.threadpool.thread.TestThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengdu
 * @date 2019/8/14.
 */
public class NewFixedPoolTest {

    public static void main(String[] args){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++){
            TestThread testThread = new TestThread("thread " + i);
            threadPool.execute(testThread);
        }
        threadPool.shutdown();
    }
}
