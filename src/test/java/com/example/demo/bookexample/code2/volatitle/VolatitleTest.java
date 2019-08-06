package com.example.demo.bookexample.code2.volatitle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * voliate 能保证数据 可见性、有序性，但不能保证原子性
 * @author chengdu
 * @date 2019/8/6.
 */
public class VolatitleTest {

    private static Logger LOGGER = LoggerFactory.getLogger(VolatitleTest.class);

    private static volatile boolean ready;

    private static volatile int number;

    private static class ReadThread2 extends Thread {

        public ReadThread2(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            while (!ready);
            LOGGER.info("number is {}", number);
        }
    }

    public static void main(String[] args) throws Exception {
        ReadThread2 readThread = new ReadThread2("---Read Thread 1---");
        readThread.start();
        LOGGER.info("number is {}", number);
        number = 100;
        ready = true;
        readThread.join();
    }
}
