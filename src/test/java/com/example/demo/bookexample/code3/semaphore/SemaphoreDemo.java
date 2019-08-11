package com.example.demo.bookexample.code3.semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class SemaphoreDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreDemo.class);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++){
            SemaThread semaThread = new SemaThread("Sema " + i);
            executorService.execute(semaThread);
        }
        executorService.shutdown();
    }
}
