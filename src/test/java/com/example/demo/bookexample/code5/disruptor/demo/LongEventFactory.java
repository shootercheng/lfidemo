package com.example.demo.bookexample.code5.disruptor.demo;

import com.lmax.disruptor.EventFactory;

/**
 * @author chengdu
 * @date 2019/9/10.
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
