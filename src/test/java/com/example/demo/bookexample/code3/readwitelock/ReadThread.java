package com.example.demo.bookexample.code3.readwitelock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class ReadThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadThread.class);

    // 多个读线程一把锁
    private static Lock lock;

    public static void setLock(Lock wlock){
        lock = wlock;
    }

    public ReadThread(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        int result = ReadWriteHandler.handleRead(lock);
        LOGGER.info("Thread id {} name {} result {}", this.getId(), this.getName(), result);
    }
}
