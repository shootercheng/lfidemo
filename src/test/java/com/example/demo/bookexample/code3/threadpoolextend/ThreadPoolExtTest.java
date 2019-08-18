package com.example.demo.bookexample.code3.threadpoolextend;

import com.example.demo.bookexample.code3.threadpool.thread.TestThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengdu
 * @date 2019/8/18.
 */
public class ThreadPoolExtTest {

    public static void main(String[] args){

        ThreadPoolExt threadPool = new ThreadPoolExt(
                5,
                8,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        for (int i = 0; i < 20; i++){
            TestThread testThread = new TestThread("testthread " + i);
            threadPool.execute(testThread);
        }
        threadPool.shutdown();
    }
}
