package com.example.demo.bookexample.code5.prdcst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author chengdu
 * @date 2019/9/9.
 */
public class Consumer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue blockingQueue, String name){
        this.blockingQueue = blockingQueue;
        super.setName(name);
    }

    @Override
    public void run() {
        while (true){
            try {
                int num = blockingQueue.take();
                LOGGER.info("【Consumer】 take num {} from queue", num);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
