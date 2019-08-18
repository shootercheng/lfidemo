package com.example.demo.bookexample.code3.threadpoolextend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author chengdu
 * @date 2019/8/18.
 */
public class ThreadPoolExt extends ThreadPoolExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolExt.class);

    public ThreadPoolExt(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        LOGGER.info("before execute thread {}", r.toString());
    }

    @Override
    public void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        LOGGER.info("after execute thread {}", r.toString());
    }

    public void terminated() {
        super.terminated();
        LOGGER.info("thread pool terminated");
    }
}
