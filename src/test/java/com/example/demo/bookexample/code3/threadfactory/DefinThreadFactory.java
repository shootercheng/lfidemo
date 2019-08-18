package com.example.demo.bookexample.code3.threadfactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengdu
 * @date 2019/8/18.
 */
public class DefinThreadFactory implements ThreadFactory {

    // 系统总共创建了多少线程
    private static final AtomicInteger threadNum = new AtomicInteger(0);

    private boolean isDaemon;

    private String poolName;

    public DefinThreadFactory(String poolName, boolean isDaemon){
        this.poolName = poolName;
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = poolName + "-" + "thiread-" + threadNum.getAndIncrement();
        Thread thread = new Thread(r, threadName);
        thread.setDaemon(isDaemon);
        return thread;
    }

    public int getSysThreadNum(){
        return threadNum.get();
    }
}
