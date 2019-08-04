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
            for (i = 0; i < 1000; i++);
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
}
