package com.example.demo.bookexample.code2.joinyeild;

import org.junit.Test;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class JoinTest {

    private static int i = 0;

    private static class AddThread extends Thread {

        @Override
        public void run(){
            for (i = 0; i < 1000000000; i++);
            // It is rarely appropriate to use this method. It may be useful
            // for debugging or testing purposes, where it may help to reproduce
            // bugs due to race conditions.
            Thread.yield();
        }
    }

    @Test
    public void testThread(){
        AddThread addThread = new AddThread();
        addThread.start();
        System.out.println(i);
    }

    @Test
    public void testThreadJoin() throws Exception {
        AddThread addThread = new AddThread();
        addThread.start();
        addThread.join();
        System.out.println(i);
    }

    @Test
    public void testThreadJoinWithTm() throws Exception{
        AddThread addThread = new AddThread();
        addThread.start();
        // addThread wait 4
        addThread.join(4);
        System.out.println(i);
    }
}
