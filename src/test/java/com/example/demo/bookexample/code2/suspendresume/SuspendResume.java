package com.example.demo.bookexample.code2.suspendresume;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/5.
 */
public class SuspendResume {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuspendResume.class);

    private static Object object = new Object();

    private static class TestThred extends Thread{
        public TestThred(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            synchronized (object){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + " is running");
                // 弃用方法，不要使用
                // 挂起
                Thread.currentThread().suspend();
            }
        }
    }

    public static class ThreadNoSuspend extends Thread {
        public ThreadNoSuspend(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " is running");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static TestThred t1 = new TestThred("---------t1");
    private static TestThred t2 = new TestThred("---------t2");


    public static void testThread1() throws Exception {
        LOGGER.info("waiting jconsole connect~~~");
        Thread.sleep(20000);
        t1.start();
        Thread.sleep(2000);
        // 唤醒
        t1.resume();
        t2.start();
        t2.resume();
    }

    /**
     * 线程优先级问题
     * @throws Exception
     */
    public static void testThread2() throws Exception {
        LOGGER.info("waiting jconsole connect~~~");
        ThreadNoSuspend threadNoSuspend1 = new ThreadNoSuspend("---------------- thread 1");
        // 越大越优先
        threadNoSuspend1.setPriority(Thread.MAX_PRIORITY);
        ThreadNoSuspend threadNoSuspend2 = new ThreadNoSuspend("---------------- thread 2");
        threadNoSuspend2.setPriority(Thread.MIN_PRIORITY);
        threadNoSuspend1.start();
        // wait threadNoSuspend1 over
//        threadNoSuspend1.join();
        threadNoSuspend2.start();
    }

    public static void main(String[] args) throws Exception {
        testThread1();
    }
}
