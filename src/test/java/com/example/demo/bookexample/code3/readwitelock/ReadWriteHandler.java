package com.example.demo.bookexample.code3.readwitelock;

import java.util.concurrent.locks.Lock;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class ReadWriteHandler {

    private static volatile int value;

    public static int handleRead(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }

    public static void handleWrite(Lock lock, int num) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = num;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
