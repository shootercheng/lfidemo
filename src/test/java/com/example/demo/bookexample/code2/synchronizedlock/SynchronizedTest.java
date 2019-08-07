package com.example.demo.bookexample.code2.synchronizedlock;

/**
 * @author chengdu
 * @date 2019/8/7.
 */
public class SynchronizedTest {

    public static void main(String[] args) throws Exception {
        ThreadSynTest threadSynTest1 = new ThreadSynTest("syn test1");
        ThreadSynTest threadSynTest2 = new ThreadSynTest("syn test2");
        threadSynTest1.start();
        threadSynTest2.start();
        threadSynTest1.join();
        threadSynTest2.join();
        System.out.println(threadSynTest1.getI());
    }
}
