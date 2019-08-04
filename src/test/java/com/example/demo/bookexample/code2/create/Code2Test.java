package com.example.demo.bookexample.code2.create;

import org.junit.Test;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class Code2Test {

    @Test
    public void testThreadRun() throws Exception {
        Thread thread = new Thread(new ThreadTest());
        // 不要使用run()方法来开启线程。它只会在当前线程中串行执行 run() 方法中的代码
        thread.run();
        for (int i = 0; i < 10; i++){
            Thread.sleep(50);
            System.out.println("main " + i);
        }
    }

    @Test
    public void testThreadStart() throws Exception {
        Thread thread = new Thread(new ThreadTest());
        thread.start();
        for (int i = 0; i < 10; i++){
            Thread.sleep(50);
            System.out.println("main " + i);
        }
    }

    @Test
    public void testStartThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, Thread");
            }
        });
        thread.start();
    }
}
