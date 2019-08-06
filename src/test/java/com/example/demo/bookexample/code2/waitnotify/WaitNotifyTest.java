package com.example.demo.bookexample.code2.waitnotify;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class WaitNotifyTest {

    private static Object object = new Object();

    private static class T1 extends Thread {

        @Override
        public void run(){

            synchronized (object){
                System.out.println(System.currentTimeMillis() + " T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + " T1 wait for object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + " T1 end");
            }
        }
    }

    private static class T2 extends Thread {

        @Override
        public void run(){

            synchronized (object){
                System.out.println(System.currentTimeMillis() + " T2 start");
                System.out.println(System.currentTimeMillis() + " T2 notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + " T2 end");
                try {
                    Thread.sleep(2000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args){
        T2 t2 = new T2();
        T1 t1 = new T1();
        t1.start();
        t2.start();
    }
}
