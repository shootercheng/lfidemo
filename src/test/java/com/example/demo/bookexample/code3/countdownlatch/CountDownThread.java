package com.example.demo.bookexample.code3.countdownlatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class CountDownThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountDownThread.class);

    private CountDownLatch countDownLatch;

    public CountDownThread(CountDownLatch countDownLatch, String name){
        this.countDownLatch = countDownLatch;
        super.setName(name);
    }

    @Override
    public void run(){
        try {
            Thread.sleep(1000);
            LOGGER.info("thread running name {}", this.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
