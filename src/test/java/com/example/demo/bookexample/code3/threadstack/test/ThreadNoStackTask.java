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
public class ThreadNoStackTask {

    public static ThreadPoolExecutor createThreadPool() {
        DefinThreadFactory definThreadFactory = new DefinThreadFactory("pool1", false);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                8,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                definThreadFactory,
                new ThreadPoolExecutor.DiscardPolicy()
        );
        return threadPoolExecutor;
    }

    public static void testSubmit(){
        ThreadPoolExecutor threadPoolExecutor = createThreadPool();
        for (int i = 0; i < 5; i++){
            DivTask divTask = new DivTask("thread " + i, 10, i);
            threadPoolExecutor.submit(divTask);
        }
        threadPoolExecutor.shutdown();
    }

    public static void testExecute(){
        ThreadPoolExecutor threadPoolExecutor = createThreadPool();
        for (int i = 0; i < 5; i++){
            DivTask divTask = new DivTask("thread " + i, 10, i);
            threadPoolExecutor.execute(divTask);
        }
        threadPoolExecutor.shutdown();
    }

    public static void main(String[] args){
        testExecute();
    }
}
