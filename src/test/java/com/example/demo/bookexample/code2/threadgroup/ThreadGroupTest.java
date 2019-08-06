package com.example.demo.bookexample.code2.threadgroup;

/**
 * @author chengdu
 * @date 2019/8/6.
 */
public class ThreadGroupTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("Thread Group Test");
        TestThread testThread1 = new TestThread(threadGroup, "test thread 1");
        TestThread testThread2 = new TestThread(threadGroup, "test thread 2");
        threadGroup.list();
        testThread1.start();
        testThread2.start();
    }
}
