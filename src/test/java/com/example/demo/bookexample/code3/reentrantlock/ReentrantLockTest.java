package com.example.demo.bookexample.code3.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/10.
 */
public class ReentrantLockTest {

    private static Logger LOGGER = LoggerFactory.getLogger(ReentrantLockTest.class);

    public static void testRLockAddI() throws InterruptedException {
        RlockThread rlockThread1 = new RlockThread("rLock thread 1");
        RlockThread rlockThread2 = new RlockThread("rLock thread 2");
        rlockThread1.start();
        rlockThread2.start();
        rlockThread1.join();
        rlockThread2.join();
        System.out.println(RlockThread.getI());
    }

    public static void testInterrupt() throws InterruptedException {
        LockInterruptThread lockInterruptThread1 = new LockInterruptThread("lockIt 1", 1);
        LockInterruptThread lockInterruptThread2 = new LockInterruptThread("lockIt 2", 2);
        lockInterruptThread1.start();
        lockInterruptThread1.start();
        lockInterruptThread1.join();
        lockInterruptThread2.join();
        lockInterruptThread2.interrupt();
        LOGGER.info("child thread over");
    }

    public static void testtryLock() throws InterruptedException {
        LOGGER.info("waiting jconsole connect~~~");
        Thread.sleep(20000);
        TryLockThreadTest tryLockThreadTest1 = new TryLockThreadTest("--------------try lock 1");
        TryLockThreadTest tryLockThreadTest2 = new TryLockThreadTest("--------------try lock 2");
        tryLockThreadTest1.start();
        tryLockThreadTest2.start();
    }

    public static void testtryLock2(){
        TryLockThreadTest2 tryLockThreadTest21 = new TryLockThreadTest2("-----lock1", 1);
        TryLockThreadTest2 tryLockThreadTest22 = new TryLockThreadTest2("-----lock2", 2);
        tryLockThreadTest21.start();
        tryLockThreadTest22.start();
    }

    public static void testFairLock() throws InterruptedException {
        FairLockThread fairLockThread1 = new FairLockThread("-------------thread 1");
        FairLockThread fairLockThread2 = new FairLockThread("-------------thread 2");
        fairLockThread1.setDaemon(true);
        fairLockThread2.setDaemon(true);
        fairLockThread1.start();
        fairLockThread2.start();
        Thread.sleep(4000);
    }

    public static void main(String[] args) throws InterruptedException {
//        testRLockAddI();
//        testInterrupt();
//        testtryLock();
//        testtryLock2();
        testFairLock();
    }
}
