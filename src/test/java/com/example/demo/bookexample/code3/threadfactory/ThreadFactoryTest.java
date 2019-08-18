package com.example.demo.bookexample.code3.threadfactory;


import java.util.concurrent.*;

/**
 * @author chengdu
 * @date 2019/8/18.
 */
public class ThreadFactoryTest {

    private static CountDownLatch countDownLatch;

    public static CountDownLatch getCountDownLatch(){
        return countDownLatch;
    }

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
//        TestThread[] threads = new TestThread[20];
//        for (int i = 0; i < 20; i++){
//            threads[i] = new TestThread("thread " + i);
//            threadPool.execute(threads[i]);
//        }
//        for (int i = 0; i < 20; i++){
//            threads[i].join();
//        }
        int threadNum = 20;
        countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++){
            ThreadCountDown threadCountDown = new ThreadCountDown("thread " + i);
            threadPool.execute(threadCountDown);
        }
        countDownLatch.await();
        System.out.println("created thread " + threadFactory.getSysThreadNum());
        System.out.println("cpus " + Runtime.getRuntime().availableProcessors());
        threadPool.shutdown();
    }
}
