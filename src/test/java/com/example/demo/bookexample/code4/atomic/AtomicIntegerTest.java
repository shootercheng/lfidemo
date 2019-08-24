package com.example.demo.bookexample.code4.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengdu
 * @date 2019/8/24.
 */
public class AtomicIntegerTest {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static Integer noatomic = 0;

    private static class AddThread extends Thread {

        public AddThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            for (int i = 0; i < 10000; i++){
                noatomic++;
                atomicInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread[] addThreads = new AddThread[10];
        for (int i = 0; i < addThreads.length; i++){
            addThreads[i] = new AddThread("thread " + i);
        }
        for (int i = 0; i < addThreads.length; i++){
            addThreads[i].start();
        }
        for (int i = 0; i < addThreads.length; i++){
            addThreads[i].join();
        }
        System.out.println(noatomic);
        System.out.println(atomicInteger);
    }
}
