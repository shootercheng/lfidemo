package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengdu
 * @date 2019/8/4.
 */

@Configuration
public class ThreadPoolConfig {

    @Value("${pool.corePoolSize}")
    private int corePoolSize;

    @Value("${pool.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${pool.keepAliveTime}")
    private long keepAliveTime;

    @Value("${pool.timeUnit}")
    private TimeUnit timeUnit;

    @Value("${pool.blockingQueue}")
    private int blockingQueue;



    @Bean(name = "threadPool")
    public ExecutorService createThreadPool(){
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, timeUnit, new ArrayBlockingQueue(blockingQueue),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
