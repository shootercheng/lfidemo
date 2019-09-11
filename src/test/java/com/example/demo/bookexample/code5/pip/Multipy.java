package com.example.demo.bookexample.code5.pip;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chengdu
 * @date 2019/9/11.
 */
public class Multipy extends Thread {

    private static final BlockingQueue<CalBase> queue = new LinkedBlockingQueue<>();

    public static void addCalBase(CalBase calBase) throws InterruptedException {
        queue.add(calBase);
    }

    private static volatile int size;

    public Multipy(String name, int size){
        super.setName(name);
        Multipy.size = size;
    }

    @Override
    public void run(){
        while (size > 0) {
            try {
                // (a * b + a) / b;
                CalBase calBase = queue.take();
                calBase.setResult(calBase.getA() * calBase.getB());
                Plus.addCalBase(calBase);
                size --;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
