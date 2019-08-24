package com.example.demo.bookexample.code4.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/24.
 */
public class BooleanTest {

//    private static boolean writeStatus = false;

    private static volatile boolean writeStatus = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(BooleanTest.class);

    private static class ReadThread extends Thread {

        public ReadThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            while (true){
                if (writeStatus) {
                    LOGGER.info("start read ......");
                    try {
                        Thread.sleep(4000);
                        LOGGER.info("end read ........");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        writeStatus = false;
                    }
                }
            }
        }
    }

    private static class WriteThread extends Thread {

        public WriteThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            LOGGER.info("start write .....");
            try {
                Thread.sleep(2000);
                LOGGER.info("end write .....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeStatus = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread("read");
        WriteThread writeThread = new WriteThread("write");
        readThread.start();
        writeThread.start();
//        readThread.join();
        writeThread.join();
        Thread.sleep(5000);
        writeStatus = true;
    }
}
