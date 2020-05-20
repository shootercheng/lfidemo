package com.example.performance.code3.reference.test;

import com.example.performance.code3.reference.model.TestBean;
import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * JVM : -Xmx5M
 * @author James
 */
public class SoftRefTest {

    /**
     * GC 在内存不足的情况下，才会去回收软引用
     * @param args
     */
    public static void main(String[] args){
        TestBean testBean = new TestBean(1L, "James");
        ReferenceQueue<TestBean> referenceQueue = new ReferenceQueue<>();
        // 如果对象回收则静茹引用队列 reference queue
        SoftReference<TestBean> softReference = new SoftReference<>(testBean, referenceQueue);
        CheckRefQueueThread checkRefQueueThread = new CheckRefQueueThread(referenceQueue);
        // 开启线程监控对象回收情况
        checkRefQueueThread.start();
        // 删除强引用
        testBean = null;
        System.gc();
        System.out.println("After GC: Soft Get= " + softReference.get());
        System.out.println("分配一块大内存........");
        byte[] bytes = new byte[4*1024*1024];
        System.out.println("After new byte[] " + softReference.get());
    }
}
