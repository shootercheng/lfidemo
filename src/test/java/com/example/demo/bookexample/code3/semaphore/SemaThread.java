package com.example.demo.bookexample.code3.semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class SemaThread extends Thread {

    private static final Semaphore semaphore = new Semaphore(5);

    private static final Logger LOGGER = LoggerFactory.getLogger(SemaThread.class);

    public SemaThread(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        try {
            semaphore.acquire();
            Thread.sleep(5000);
            LOGGER.info("Thread id {} name {} running", this.getId(), this.getName());
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
