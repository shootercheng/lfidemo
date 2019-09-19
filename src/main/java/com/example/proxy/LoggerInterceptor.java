package com.example.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/9/19.
 */
public class LoggerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);

    public void before(String name) {
        LOGGER.info("source name {}, start time {}", name, System.currentTimeMillis());
    }

    public void after(String name) {
        LOGGER.info("source name {}, end time {}", name, System.currentTimeMillis());
    }
}
