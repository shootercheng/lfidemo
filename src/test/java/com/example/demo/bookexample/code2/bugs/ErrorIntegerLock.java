package com.example.demo.bookexample.code2.bugs;

/**
 * @author chengdu
 * @date 2019/8/7.
 */
public class ErrorIntegerLock {

    private static volatile Integer i = 0;

    private static class ThreadTest extends Thread {

        private static Object object = new Object();

        public ThreadTest(String name){
            super.setName(name);
        }

        @Override
        public void run(){
//            synchronized (object)
            synchronized (i){
                for (int j = 0; j < 10000; j++) {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest1 = new ThreadTest("thread test1");
        ThreadTest threadTest2 = new ThreadTest("thread test2");
        threadTest1.start();
        threadTest2.start();
        threadTest1.join();
        threadTest2.join();
        System.out.println(i);
    }
}
