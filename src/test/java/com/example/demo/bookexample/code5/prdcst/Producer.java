package com.example.demo.bookexample.code5.prdcst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author chengdu
 * @date 2019/9/9.
 */
public class Producer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue blockingQueue, String name){
        this.blockingQueue = blockingQueue;
        super.setName(name);
    }

    @Override
    public void run() {
        while (true){
            int num = RandomNum.genRandomInteger(100);
            LOGGER.info("【Producer】 put number {} ", num);
            try {
                blockingQueue.put(num);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
