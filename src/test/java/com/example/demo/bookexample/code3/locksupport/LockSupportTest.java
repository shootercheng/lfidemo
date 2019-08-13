package com.example.demo.bookexample.code3.locksupport;

import com.example.demo.bookexample.code2.suspendresume.SuspendResume;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

/**
 * @author chengdu
 * @date 2019/8/13.
 */
public class LockSupportTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuspendResume.class);

    private static Object object = new Object();

    private static class TestThred extends Thread{
        public TestThred(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            synchronized (object){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info("Thread name {} is running", getName());
//                Thread.currentThread().suspend();
                LockSupport.park();
                if (Thread.interrupted()){
                    LOGGER.info("Thread name {} interrupted", getName());
                }
                LOGGER.info("Thread name {} unparked", getName());
            }
        }
    }

    private static TestThred t1 = new TestThred("---------t1");
    private static TestThred t2 = new TestThred("---------t2");

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(2000);
        // 唤醒 or 中断 Test
        LockSupport.unpark(t1);
//        t1.interrupt();
        t2.start();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
        LOGGER.info("child thread over");
    }

}
