package com.example.demo.threadlocal;

import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author James
 */
public class ThreadLocalTest {

    private ExecutorService threadPool =  new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1));

    public void testParentChildThread() throws InterruptedException {
        TransmittableClazz.setValue("1");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        threadPool.execute(TtlRunnable.get(() -> {
            try {
                System.out.println("thread info " + Thread.currentThread().getName());
                System.out.println("parent value ----" + TransmittableClazz.getValue());
            } finally {
                countDownLatch.countDown();
            }
        }));
        Thread.sleep(2000);
        TransmittableClazz.setValue("2");
        threadPool.execute(TtlRunnable.get(() -> {
            try {
                System.out.println("thread info " + Thread.currentThread().getName());
                System.out.println("parent value ----" + TransmittableClazz.getValue());
            } finally {
                countDownLatch.countDown();
            }
        }));
        countDownLatch.await();
        threadPool.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocalTest.testParentChildThread();
    }
}
