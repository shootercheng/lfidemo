package com.example.demo.bookexample.code3.threadfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chengdu
 * @date 2019/8/18.
 */
public class DefinRejectedExecutionHandler implements RejectedExecutionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefinRejectedExecutionHandler.class);

    private String poolName;

    public DefinRejectedExecutionHandler(String poolName){
        this.poolName = poolName;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        ThreadFactoryTest.getCountDownLatch().countDown();
        LOGGER.warn("threadPool {} rejectd thread {}", poolName, r.toString());
    }
}
