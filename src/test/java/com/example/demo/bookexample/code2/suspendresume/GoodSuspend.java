package com.example.demo.bookexample.code2.suspendresume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/6.
 */
public class GoodSuspend {

    private static Logger LOGGER = LoggerFactory.getLogger(GoodSuspend.class);

    private static volatile String state = new String();

    private static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name){
            super.setName(name);
        }

        private volatile boolean isSuspend;

        public void susPendThread(){
            isSuspend = true;
        }

        public void resumeThread(){
            isSuspend = false;
            // 获取锁之后，唤醒 WAITING Thread
            // synchronized 必须添加
            synchronized (this) {
                notify();
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (isSuspend){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (state) {
                    state = "running";
//                    System.out.println("set state " + state);
                    LOGGER.info("set state "+ state);
                }
            }
        }
    }

    private static class ReadObjectThread extends Thread {

        public ReadObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            while (true){
                synchronized (state){
//                    System.out.println("read state " + state);
                    LOGGER.info("read state " + state);
                }
            }
        }
    }

    public static void testSuspend() throws Exception {
        LOGGER.info("waiting jconsole connect~~~");
        Thread.sleep(20000);
        ChangeObjectThread changeObjectThread = new ChangeObjectThread("Change Object Thread--");
        ReadObjectThread readObjectThread = new ReadObjectThread("Read Object Thread--");
        changeObjectThread.start();
        readObjectThread.start();
        Thread.sleep(1000);
        changeObjectThread.susPendThread();
    }

    public static void testResume() throws Exception {
        LOGGER.info("waiting jconsole connect~~~");
        Thread.sleep(20000);
        ChangeObjectThread changeObjectThread = new ChangeObjectThread("Change Object Thread--");
        ReadObjectThread readObjectThread = new ReadObjectThread("Read Object Thread--");
        changeObjectThread.start();
        readObjectThread.start();
        Thread.sleep(1000);
        changeObjectThread.susPendThread();
        Thread.sleep(2000);
        changeObjectThread.resumeThread();
    }

    public static void main(String[] args) throws Exception {
        testResume();
    }
}
