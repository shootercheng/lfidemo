package com.example.demo.bookexample.code3.ratelimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/13.
 */
public class RateThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateThread.class);

    public RateThread(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        LOGGER.info("Thread id {} name {} running", this.getId(), this.getName());
    }
}
