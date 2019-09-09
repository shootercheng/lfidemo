package com.example.demo.bookexample.code5.prdcst;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author chengdu
 * @date 2019/9/9.
 */
public class PrdCstTest {

    public static void testBlock(BlockingQueue<Integer> blockingQueue){
        Producer producer = new Producer(blockingQueue, "producer");
        producer.start();
    }
    public static void testConsumerBlock(BlockingQueue<Integer> blockingQueue){
        Consumer consumer1 = new Consumer(blockingQueue, "consumer1");
        Consumer consumer2 = new Consumer(blockingQueue, "consumer2");
        consumer1.start();
        consumer2.start();
    }

    public static void testBalance(BlockingQueue<Integer> blockingQueue){
        // 1 个生产者 Producer 2 个消费者 Consumer
        Producer producer = new Producer(blockingQueue, "producer");
        Consumer consumer1 = new Consumer(blockingQueue, "consumer1");
        Consumer consumer2 = new Consumer(blockingQueue, "consumer2");
        producer.start();
        consumer1.start();
        consumer2.start();
    }

    public static void main(String[] args){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);
//        testBlock(blockingQueue);
//        testConsumerBlock(blockingQueue);
        testBalance(blockingQueue);
    }
}
