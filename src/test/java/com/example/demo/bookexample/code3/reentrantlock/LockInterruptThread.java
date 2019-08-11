package com.example.demo.bookexample.code3.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengdu
 * @date 2019/8/10.
 */
public class LockInterruptThread extends Thread {

    private static final ReentrantLock rLock1 = new ReentrantLock();

    private static final ReentrantLock rLock2 = new ReentrantLock();

    private static final Logger LOGGER = LoggerFactory.getLogger(LockInterruptThread.class);

    private int i;

    public LockInterruptThread(String name, int i){
        super.setName(name);
        this.i = i;
    }

    @Override
    public void run(){
        try{
        if (i == 1){
                rLock1.lockInterruptibly();
                Thread.sleep(2000);
                rLock2.lockInterruptibly();
        } else {
                rLock2.lockInterruptibly();
                Thread.sleep(2000);
                rLock1.lockInterruptibly();
        }
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {

            if (rLock1.isHeldByCurrentThread()){
                rLock1.unlock();
            }
            if (rLock2.isHeldByCurrentThread()){
                rLock2.unlock();
            }
            LOGGER.info("Thread id {}, name {} over", this.getId(), this.getName());
        }
    }
}
