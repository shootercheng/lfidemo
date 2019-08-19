package com.example.demo.bookexample.code3.threadstack.test;

import com.example.demo.bookexample.code3.threadfactory.DefinThreadFactory;
import com.example.demo.bookexample.code3.threadstack.task.DivTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengdu
 * @date 2019/8/19.
 */
public class ThreadStackLocation {

    public static void main(String[] args){
        DefinThreadFactory definThreadFactory = new DefinThreadFactory("pool1", false);
        ThreadPoolExtException threadPoolExecutor = new ThreadPoolExtException(
                5,
                8,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                definThreadFactory,
                new ThreadPoolExecutor.DiscardPolicy()
        );
        for (int i = 0; i < 5; i++){
            DivTask divTask = new DivTask("thread " + i, 10, i);
            threadPoolExecutor.execute(divTask);
        }
        threadPoolExecutor.shutdown();
    }
}
