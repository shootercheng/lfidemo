package com.example.demo.bookexample.code4.threadlocal;


import com.example.demo.bookexample.code4.threadlocal.thread.RandomThread;
import com.google.common.util.concurrent.MoreExecutors;
import org.openjdk.jmh.runner.RunnerException;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chengdu
 * @date 2019/8/24.
 */
public class RandomTest {

    private static final ExecutorService threadPool = MoreExecutors
            .getExitingExecutorService((ThreadPoolExecutor) Executors.newFixedThreadPool(4));

    public static void testMode1(){
        for(int i = 0; i < 4; i++){
            threadPool.execute(new RandomThread("rd-"+i, 1));
        }
    }


    public static void testMode2(){
        for (int i = 0; i <4; i++){
            threadPool.execute(new RandomThread("rd-"+i, 2));
        }
    }

    public static void main(String[] args) throws RunnerException {
        testMode1();
        testMode2();
    }
}
