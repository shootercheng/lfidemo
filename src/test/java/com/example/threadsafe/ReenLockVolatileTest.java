package com.example.threadsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author James
 */
public class ReenLockVolatileTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileTest.class);

    private static volatile boolean isRunning = false;

    private static Lock lock = new ReentrantLock();

    public static void testThreadSafe(){
        if (!isRunning){
            if (!lock.tryLock()) {
                LOGGER.info("handler is running " + "locked");
                return;
            }
            lock.lock();
            if (!isRunning) {
                LOGGER.info("start running---------------");
                isRunning = true;
                try {
                    Thread.sleep(4000);
                    LOGGER.info("end running------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    isRunning = false;
                }
            }
        } else {
            LOGGER.info("handler is running");
        }
    }
}
