package com.example.demo.bookexample.code2.create;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class ThreadTest implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}
