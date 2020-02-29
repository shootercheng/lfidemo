package com.example.threadsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengdu
 * @date 2019/8/25.
 */
public class VolatileSafeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileTest.class);

    private static volatile boolean isRunning = false;

    private static Lock lock = new ReentrantLock();

    public static void testSafeVolatile(){
        if (!isRunning){
            lock.lock();
            if (!isRunning) {
                LOGGER.info("start running---------------");
                isRunning = true;
                lock.unlock();
                try {
                    Thread.sleep(4000);
                    LOGGER.info("end running------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    isRunning = false;
                }
            } else {
                lock.unlock();
                LOGGER.info("handler is running");
            }
        } else {
            LOGGER.info("handler is running");
        }
    }
}
