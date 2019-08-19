package com.example.demo.bookexample.code3.threadstack.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/19.
 */
public class DivTask extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(DivTask.class);

    private int a;

    private int b;

    public DivTask(String name, int a, int b){
        super.setName(name);
        this.a = a;
        this.b = b;
    }

    @Override
    public void run(){
        int c = a / b;
        LOGGER.info("Thread Name {}, {} / {} result {}", this.getName(), a, b, c);
    }
}
