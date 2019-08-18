package com.example.demo.bookexample.code3.threadfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/18.
 */
public class ThreadCountDown extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadCountDown.class);

    public ThreadCountDown(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        LOGGER.info("Thread Info Id {} Name {} Time {}", Thread.currentThread().getId(),
                this.getName(), System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ThreadFactoryTest.getCountDownLatch().countDown();
        }
    }
}
