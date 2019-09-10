package com.example.demo.bookexample.code5.disruptor.demo;

/**
 * @author chengdu
 * @date 2019/9/10.
 */
public class LongEvent {

    private Long value;

    public void setValue(long value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
