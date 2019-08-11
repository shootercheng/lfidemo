package com.example.demo.bookexample.code3.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class ConditionTest {

    private static final ReentrantLock rLock = new ReentrantLock();

    private static final Condition condition = rLock.newCondition();

    private static final Logger LOGGER = LoggerFactory.getLogger(ConditionTest.class);

    private static class TestThread extends Thread {

        public  TestThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            rLock.lock();
            try{
                // 当前线程等待，同时释放当前锁
                condition.await();
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                rLock.unlock();
            }
            LOGGER.info("Thread id {}, name {} over", this.getId(), this.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("waiting jconsole connect~~~");
        Thread.sleep(20000);
        TestThread testThread = new TestThread("------------condition test");
        testThread.start();
        Thread.sleep(2000);
        rLock.lock();
        // 唤醒等待的线程
        condition.signal();
        rLock.unlock();
        testThread.join();
        LOGGER.info("main thread over");
    }
}
