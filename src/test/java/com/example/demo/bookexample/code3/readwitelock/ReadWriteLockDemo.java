package com.example.demo.bookexample.code3.readwitelock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class ReadWriteLockDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadWriteLockDemo.class);

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock witeLock = readWriteLock.writeLock();

    public static void main(String[] args){

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
//                ReadWriteHandler.handleWrite(reentrantLock, new Random().nextInt() + 1);
                ReadWriteHandler.handleWrite(witeLock, new Random().nextInt() + 1);
                LOGGER.info("write success");
            }
        };

        Runnable readRunable = new Runnable() {
            @Override
            public void run() {
//                int result = ReadWriteHandler.handleRead(reentrantLock);
                int result = ReadWriteHandler.handleRead(readLock);
                LOGGER.info("read success {}", result);
            }
        };

        // 启动写线程
        for (int i = 0; i < 2; i++){
           Thread thread = new Thread(writeRunnable);
           thread.setPriority(8);
           thread.start();
        }

        // 启动读线程
        for (int i = 0; i < 20; i++){
            new Thread(readRunable).start();
        }
    }
}
