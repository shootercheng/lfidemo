package com.example.demo.bookexample.code3.threadpool.rejecthandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/16.
 */
public class ThreadTask extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTask.class);

    public ThreadTask(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        try {
            Thread.sleep(1000);
            LOGGER.info("Thread Name {} Id {}", this.getName(), Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
