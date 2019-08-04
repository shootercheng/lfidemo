package com.example.service.impl;

import com.example.service.AsyncTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
@Service
public class AsyncTestServiceImpl implements AsyncTestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTestServiceImpl.class);

    @Override
    public void asyncTest() {
        LOGGER.info("start test....");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("end test.....");
    }
}
