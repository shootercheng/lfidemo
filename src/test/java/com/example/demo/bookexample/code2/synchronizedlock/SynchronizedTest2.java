package com.example.demo.bookexample.code2.synchronizedlock;

/**
 * @author chengdu
 * @date 2019/8/7.
 */
public class SynchronizedTest2 {

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        ThreadRunTest threadRun = new ThreadRunTest(i);
        Thread thread1 = new Thread(threadRun);
        Thread thread2 = new Thread(threadRun);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(threadRun.getI());
    }
}
