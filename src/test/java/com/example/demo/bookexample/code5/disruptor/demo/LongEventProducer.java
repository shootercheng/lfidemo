package com.example.demo.bookexample.code5.disruptor.demo;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author chengdu
 * @date 2019/9/10.
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer b){
        long sequence = ringBuffer.next();
        try {
            LongEvent longEvent = ringBuffer.get(sequence);
            longEvent.setValue(b.getLong(0));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
