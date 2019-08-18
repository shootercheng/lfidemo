package com.example.demo.bookexample.code3.threadfactory;

import com.example.demo.bookexample.code3.threadpool.thread.TestThread;

import java.util.concurrent.*;

/**
 * @author chengdu
 * @date 2019/8/18.
 */
public class ThreadFactoryTest {


    public static ThreadPoolExecutor createThreadPool(ThreadFactory threadFactory,
                                                      RejectedExecutionHandler handler){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                8,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                threadFactory,
                handler
        );
        return threadPoolExecutor;
    }

    public static void main(String[] args) throws InterruptedException {
        String poolName = "pool-1";
        boolean isDaemon = false;
        DefinThreadFactory threadFactory = new DefinThreadFactory(poolName, isDaemon);
        RejectedExecutionHandler rejectedExecutionHandler = new DefinRejectedExecutionHandler(poolName);
        ThreadPoolExecutor threadPool = createThreadPool(threadFactory, rejectedExecutionHandler);
        TestThread[] threads = new TestThread[20];
        for (int i = 0; i < 20; i++){
            threads[i] = new TestThread("thread " + i);
            threadPool.execute(threads[i]);
        }
        for (int i = 0; i < 20; i++){
            threads[i].join();
        }
        System.out.println("created thread " + threadFactory.getSysThreadNum());
        threadPool.shutdown();
    }
}
