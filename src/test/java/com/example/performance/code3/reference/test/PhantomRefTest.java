package com.example.performance.code3.reference.test;

import com.example.performance.code3.reference.model.TestBean;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author James
 */
public class PhantomRefTest {

    /**
     * 虚引用必须和引用队列一起使用，
     * 它的作用在于跟踪垃圾回收过程
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        TestBean testBean = new TestBean(1L, "James");
        ReferenceQueue<TestBean> referenceQueue = new ReferenceQueue<>();
        // 如果对象回收则进入引用队列 reference queue
        PhantomReference<TestBean> softReference = new PhantomReference<>(testBean, referenceQueue);
        System.out.println("phantom reference get :" + softReference.get());
        CheckRefQueueThread checkRefQueueThread = new CheckRefQueueThread(referenceQueue);
        // 开启线程监控对象回收情况
        checkRefQueueThread.start();
        // 删除强引用
        testBean = null;
        int i = 1;
        while (true) {
            System.out.println("第" + (i++) + "次 GC");
            System.gc();
            Thread.sleep(1000);
        }
    }
}
