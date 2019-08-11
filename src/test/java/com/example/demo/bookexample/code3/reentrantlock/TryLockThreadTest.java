package com.example.demo.bookexample.code3.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengdu
 * @date 2019/8/10.
 */
public class TryLockThreadTest extends Thread {

    private static final ReentrantLock rLock = new ReentrantLock();

    private static final Logger LOGGER = LoggerFactory.getLogger(TryLockThreadTest.class);

    public TryLockThreadTest(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        try {
            if (rLock.tryLock(15000, TimeUnit.MILLISECONDS)) {
                Thread.sleep(20000);
            } else {
                LOGGER.info("can not get lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }
    }
}
