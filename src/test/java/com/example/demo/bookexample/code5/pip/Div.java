package com.example.demo.bookexample.code5.pip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chengdu
 * @date 2019/9/11.
 */
public class Div extends Thread {

    private static final BlockingQueue<CalBase> queue = new LinkedBlockingQueue<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(Div.class);

    public static void addCalBase(CalBase calBase) throws InterruptedException {
        queue.put(calBase);
    }

    private int size;

    public Div(String name, int size){
        super.setName(name);
        this.size = size;
    }

    @Override
    public void run(){
        while (size > 0) {
            try {
                CalBase calBase = queue.take();
                calBase.setResult(calBase.getResult() / calBase.getB());
                size --;
                LOGGER.info("result {}", calBase);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
