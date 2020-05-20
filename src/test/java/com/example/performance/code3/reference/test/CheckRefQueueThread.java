package com.example.performance.code3.reference.test;

import com.example.performance.code3.reference.model.TestBean;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @author James
 */
public class CheckRefQueueThread extends Thread {
    private ReferenceQueue<TestBean> refernceQueue;

    public CheckRefQueueThread(ReferenceQueue<TestBean> refernceQueue) {
        this.refernceQueue = refernceQueue;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("running..........");
            if (refernceQueue != null) {
                Reference<TestBean> testBeanRef = null;
                try {
                    testBeanRef = (Reference<TestBean>) refernceQueue.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (testBeanRef != null) {
                    System.out.println("test bean reference is " + testBeanRef.get());
                }
            }
        }
    }
}
