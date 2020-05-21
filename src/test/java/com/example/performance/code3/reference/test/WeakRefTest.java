package com.example.performance.code3.reference.test;

import com.example.performance.code3.reference.model.TestBean;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author James
 */
public class WeakRefTest {

    /**
     * 弱引用
     * @param args
     */
    public static void main(String[] args) {
        TestBean testBean = new TestBean(1L, "James");
        ReferenceQueue<TestBean> referenceQueue = new ReferenceQueue<>();
        // 如果对象回收则进入引用队列 reference queue
        WeakReference<TestBean> softReference = new WeakReference<>(testBean, referenceQueue);
        CheckRefQueueThread checkRefQueueThread = new CheckRefQueueThread(referenceQueue);
        // 开启线程监控对象回收情况
        checkRefQueueThread.start();
        // 删除强引用
        testBean = null;
        System.out.println("Before GC:" + softReference.get());
        System.gc();
        System.out.println("After GC: " + softReference.get());
    }
}
