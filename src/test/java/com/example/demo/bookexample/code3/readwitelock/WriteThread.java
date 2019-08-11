package com.example.demo.bookexample.code3.readwitelock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class WriteThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteThread.class);

    private static Lock lock;

    public static void setLock(Lock wLock){
        lock = wLock;
    }

    public WriteThread(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        ReadWriteHandler.handleWrite(lock, new Random().nextInt());
        LOGGER.info("write success thread id {} name {}", this.getId(), this.getName());
    }
}
