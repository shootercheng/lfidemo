package com.example.demo.bookexample.code3.threadpool.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/14.
 */
public class TestThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestThread.class);

    public TestThread(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        try {
            Thread.sleep(1000);
            LOGGER.info("Thread Info Id {} Name {} Time {}", Thread.currentThread().getId(),
                    this.getName(), System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
