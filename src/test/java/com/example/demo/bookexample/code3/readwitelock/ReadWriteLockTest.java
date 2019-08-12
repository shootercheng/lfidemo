package com.example.demo.bookexample.code3.readwitelock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chengdu
 * @date 2019/8/11.
 */
public class ReadWriteLockTest {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock witeLock = readWriteLock.writeLock();

    public static void readWriteTest(){
        WriteThread.setLock(witeLock);
        WriteThread writeThread1 = new WriteThread("------------write thread 1");
        WriteThread writeThread2 = new WriteThread("------------write thread 2");
        writeThread1.setPriority(9);
        writeThread2.setPriority(4);
        writeThread1.start();
        writeThread2.start();

        ReadThread.setLock(readLock);
        for (int i = 0; i < 18; i++){
            ReadThread readThread = new ReadThread("read  thread "+i);
            if (i >= 8){
                readThread.setPriority(3);
            }
            readThread.start();
        }
    }

    public static void reentrLockTest(){
        WriteThread.setLock(reentrantLock);
        WriteThread writeThread1 = new WriteThread("------------write thread 1");
        WriteThread writeThread2 = new WriteThread("------------write thread 2");
        writeThread1.setPriority(10);
        writeThread2.setPriority(4);
        writeThread1.start();
        writeThread2.start();

        // 默认优先级为 5
        ReadThread.setLock(reentrantLock);
        for (int i = 0; i < 18; i++){
            ReadThread readThread = new ReadThread("read  thread "+i);
            if (i >= 8){
                readThread.setPriority(2);
            }
            readThread.start();
        }
    }

    public static void main(String[] args){
        readWriteTest();
//        reentrLockTest();
    }
}
