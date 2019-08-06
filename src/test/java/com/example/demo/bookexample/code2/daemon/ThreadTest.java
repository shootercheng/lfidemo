package com.example.demo.bookexample.code2.daemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/6.
 */
public class ThreadTest extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest.class);

    public ThreadTest(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        while (true){
            LOGGER.info("running");
        }
    }
}
