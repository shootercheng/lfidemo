package com.example.demo.bookexample.code3.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengdu
 * @date 2019/8/10.
 */
public class TryLockThreadTest2 extends Thread {

    private static final ReentrantLock rLock1 = new ReentrantLock();

    private static final ReentrantLock rLock2 = new ReentrantLock();

    private static final Logger LOGGER = LoggerFactory.getLogger(TryLockThreadTest2.class);

    private int i;

    public TryLockThreadTest2(String name, int i){
        super.setName(name);
        this.i = i;
    }

    @Override
    public void run(){
        if (i == 1){
            if (rLock1.tryLock()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (rLock2.tryLock()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        rLock2.unlock();
                    }
                }
                if (rLock1.isHeldByCurrentThread()) {
                    rLock1.unlock();
                }
                LOGGER.info("thread id {}, name {}", this.getId(), this.getName());
            }
        } else {

            if (rLock2.tryLock()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (rLock1.tryLock()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        rLock1.unlock();
                    }
                }
                rLock2.unlock();
                LOGGER.info("thread id {}, name {}", this.getId(), this.getName());
            }
        }
    }
}
