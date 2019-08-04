package com.example.demo.bookexample.code2.interrupt;

import org.junit.Test;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class InterruptTest {

    private static class TestThread extends Thread {

        @Override
        public void run(){
            while (true){
                System.out.println("running");
            }
        }
    }

    private static class TestThread2 extends Thread {

        @Override
        public void run(){
            while (true){
                System.out.println("RUNNING");
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("interrupted");
                }
                break;
            }
        }
    }

    private static class TestThread3 extends Thread {

        @Override
        public void run(){
            while (true){
                System.out.println("RUNNING");
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("interrupted");
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 设置中断标志
                    Thread.currentThread().interrupt();
                }
                break;
            }
        }
    }

    @Test
    public void testInterrupt() throws Exception{
        TestThread testThread = new TestThread();
        testThread.start();
        testThread.interrupt();
        Thread.sleep(100);
    }

    @Test
    public void testInterrupt2() throws Exception {
        TestThread2 testThread = new TestThread2();
        testThread.start();
        testThread.interrupt();
        Thread.sleep(100);
    }

    @Test
    public void testInterrupt3() throws Exception {
        TestThread3 testThread = new TestThread3();
        testThread.start();
        testThread.interrupt();
        Thread.sleep(100);
    }
}
