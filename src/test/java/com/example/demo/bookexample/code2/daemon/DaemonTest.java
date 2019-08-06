package com.example.demo.bookexample.code2.daemon;

/**
 * @author chengdu
 * @date 2019/8/6.
 */
public class DaemonTest {

    public static void threadNoDaemoTest(){
        ThreadTest threadTest = new ThreadTest("not set daemon");
        threadTest.start();
    }

    public static void threadDemoTest(){
        ThreadTest threadTest = new ThreadTest("daemon");
        threadTest.setDaemon(true);
        threadTest.start();
    }

    public static void main(String[] args) throws InterruptedException {
//        threadNoDaemoTest();
        threadDemoTest();
        Thread.sleep(4000);
    }
}
