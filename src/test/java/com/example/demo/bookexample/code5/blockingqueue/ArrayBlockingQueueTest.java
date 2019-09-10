package com.example.demo.bookexample.code5.blockingqueue;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author chengdu
 * @date 2019/9/9.
 */
public class ArrayBlockingQueueTest {

    //
    public BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);

    @Test
    public void testAdd(){
        blockingQueue.add("1");
        blockingQueue.add("2");
        blockingQueue.add("3");
        blockingQueue.add("4");
        blockingQueue.add("5");
        Assert.assertEquals(5, blockingQueue.size());
        // Queue full
        try {
            blockingQueue.add("6");
            Assert.fail("bug--------------------");
        } catch (Exception e){
            e.printStackTrace();
            Assert.assertEquals("Queue full", e.getMessage());
        }
    }

    @Test
    public void testPutBlock() throws InterruptedException {
        blockingQueue.put("1");
        blockingQueue.put("2");
        blockingQueue.put("3");
        blockingQueue.put("4");
        blockingQueue.put("5");
        Assert.assertEquals(5, blockingQueue.size());
        // 阻塞等待某个线程从队列中移除一个数据
        blockingQueue.put("6");
    }

    @Test
    public void testPutNotBlock() throws InterruptedException {
        blockingQueue.put("1");
        blockingQueue.put("2");
        blockingQueue.put("3");
        blockingQueue.put("4");
        blockingQueue.put("5");
        Assert.assertEquals(5, blockingQueue.size());
        String head = blockingQueue.poll();
        Assert.assertEquals("1", head);
        blockingQueue.put("6");
    }

    @Test
    public void testOffer() {
        blockingQueue.offer("1");
        blockingQueue.offer("2");
        blockingQueue.offer("3");
        blockingQueue.offer("4");
        blockingQueue.offer("5");
        blockingQueue.offer("6");
        try {
            blockingQueue.offer("6", 3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Assert.assertEquals(5, blockingQueue.size());
    }

    @Test
    public void testTake() throws InterruptedException {
        blockingQueue.offer("1");
        blockingQueue.offer("2");
        Assert.assertEquals("1", blockingQueue.take());
        Assert.assertEquals("2", blockingQueue.take());
        // if this queue is empty
        // waiting if necessary until an element becomes available.
        blockingQueue.take();
    }

    @Test
    public void testPoll() throws InterruptedException {
        blockingQueue.offer("1");
        blockingQueue.offer("2");
        Assert.assertEquals("1", blockingQueue.poll());
        Assert.assertEquals("2", blockingQueue.poll());
        // returns {@code null} if this queue is empty
        Assert.assertEquals(null, blockingQueue.poll());
        // wait 3 seconds
        Assert.assertEquals(null, blockingQueue.poll(3000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void testSeqs(){
        int seqs = 10;
        int queueSize = 8;
        int index = seqs & (queueSize - 1);
        System.out.println(index);
    }
}
