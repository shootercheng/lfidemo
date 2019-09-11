package com.example.demo.bookexample.code5.disruptor.demo;

import com.lmax.disruptor.WorkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/9/11.
 */
public class Consumer implements WorkHandler<LongEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        LOGGER.info("Event {}", longEvent);
    }
}
