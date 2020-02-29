package com.example.threadsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/25.
 */
public class VolatileTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileTest.class);

    private static volatile boolean isRunning = false;

    public static void testVolatile(){
        if (!isRunning){
            LOGGER.info("start running---------------");
            isRunning = true;
            try {
                Thread.sleep(4000);
                LOGGER.info("end running------------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isRunning = false;
            }
        } else {
            LOGGER.info("handler is running");
        }
    }
}
