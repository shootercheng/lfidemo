package com.example.demo.bookexample.code5.disruptor.demo;


import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/9/10.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LongEventHandler.class);

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        LOGGER.info("Event {} Sequence {} endOfBatch {}", longEvent, l, b);
    }
}
