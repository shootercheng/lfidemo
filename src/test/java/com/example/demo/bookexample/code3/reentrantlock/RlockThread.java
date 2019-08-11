package com.example.demo.bookexample.code3.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengdu
 * @date 2019/8/10.
 */
public class RlockThread extends Thread {

    public RlockThread(String name){
        super.setName(name);
    }

    private static final ReentrantLock rLock = new ReentrantLock();

    private static volatile int i = 0;

    public static int getI(){
        return i;
    }

    @Override
    public void run(){
        rLock.lock();
//        rLock.lock();
        try {
            for (int j = 0; j < 10000; j++) {
                i ++;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            rLock.unlock();
//            rLock.unlock();
        }
    }
}
