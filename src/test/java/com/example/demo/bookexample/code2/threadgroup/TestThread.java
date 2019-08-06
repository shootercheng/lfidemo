package com.example.demo.bookexample.code2.threadgroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/6.
 */
public class TestThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestThread.class);

    public TestThread(ThreadGroup threadGroup, String name){
        super(threadGroup, new Thread(), name);
    }

    @Override
    public void run(){
        ThreadGroup threadGroup = getThreadGroup();
        String threadGroupName = threadGroup.getName();
        LOGGER.info("group name {}, thread name {}", threadGroupName, Thread.currentThread().getName());
    }
}
