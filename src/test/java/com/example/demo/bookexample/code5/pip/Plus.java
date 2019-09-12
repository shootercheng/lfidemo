package com.example.demo.bookexample.code5.pip;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chengdu
 * @date 2019/9/11.
 */
public class Plus extends Thread {

    private static final BlockingQueue<CalBase> queue = new LinkedBlockingQueue<>();

    public static void addCalBase(CalBase calBase) throws InterruptedException {
        queue.put(calBase);
    }

    private int size;

    public Plus(String name, int size){
        super.setName(name);
        this.size = size;
    }

    @Override
    public void run(){
        while (size > 0) {
            try {
                // (a * b + a) / b
                CalBase calBase = queue.take();
                calBase.setResult(calBase.getResult() + calBase.getA());
                Div.addCalBase(calBase);
                size --;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
