package com.example.demo.bookexample.code3.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengdu
 * @date 2019/8/10.
 */
public class FairLockThread extends Thread {

    private static final ReentrantLock fairLock = new ReentrantLock(true);

    // 默认情况下为 false
    private static final ReentrantLock unfairLock = new ReentrantLock();

    private static final Logger LOGGER = LoggerFactory.getLogger(FairLockThread.class);


    public FairLockThread(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        while (true){
            try {
                fairLock.lock();
//                unfairLock.lock();
                LOGGER.info("Thread id {} name {} running", this.getId(), this.getName());
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                fairLock.unlock();
//                unfairLock.lock();
            }

        }
    }
}
