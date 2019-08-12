package com.example.demo.bookexample.code3.cyclicbarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author chengdu
 * @date 2019/8/12.
 */
public class Soldier extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Soldier.class);

    private static CyclicBarrier cyclicBarrier;

    public static void setCyclic(CyclicBarrier barrier){
        cyclicBarrier = barrier;
    }

    public Soldier(String name){
        super.setName(name);
    }

    private static class RandomHolder {
        static final SecureRandom secureRandom = new SecureRandom();
    }

    @Override
    public void run() {
        LOGGER.info("soldier {} running", this.getName());
        try {
            // 等待集合
            cyclicBarrier.await();
            doWork();
            // 等待任务完成
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doWork() throws InterruptedException {
        int j = RandomHolder.secureRandom.nextInt(10) + 5;
        LOGGER.info("soldier working name {}", this.getName());
        Thread.sleep(1000L * j);
    }
}
