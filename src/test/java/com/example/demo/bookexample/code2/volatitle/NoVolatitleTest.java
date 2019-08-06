package com.example.demo.bookexample.code2.volatitle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/6.
 */
public class NoVolatitleTest {

    private static Logger LOGGER = LoggerFactory.getLogger(NoVolatitleTest.class);

    private static boolean ready;

    private static int number;

    private static class ReadThread extends Thread {

        public ReadThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            while (!ready);
            LOGGER.info("number is {}", number);
        }
    }

    public static void main(String[] args) throws Exception {
        ReadThread readThread = new ReadThread("---Read Thread 1---");
        readThread.start();
        LOGGER.info("number is {}", number);
        number = 100;
        ready = true;
        readThread.join();
    }
}
