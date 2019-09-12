package com.example.demo.bookexample.code4.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengdu
 * @date 2019/9/12.
 */
public class AtomicIntegerMethodTest {

    private static AtomicInteger automicInteger = new AtomicInteger(-1);

    @Test
    public void testSetGet(){
        automicInteger.set(1);
        Assert.assertEquals(1, automicInteger.get());
    }

    @Test
    public void testCompareAndSet(){
        automicInteger.set(1);
        Assert.assertTrue(automicInteger.compareAndSet(1, 1));
    }
}
