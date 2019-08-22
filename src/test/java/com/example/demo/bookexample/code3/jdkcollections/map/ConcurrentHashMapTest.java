package com.example.demo.bookexample.code3.jdkcollections.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengdu
 * @date 2019/8/22.
 */
public class ConcurrentHashMapTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentHashMapTest.class);

    private static Map<String, Long> map = new HashMap<>();

    private static Map<String, Long> ctmap = new ConcurrentHashMap<>();

    private static class WriteThread extends Thread{

        public WriteThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            for (int i = 0; i < 100000; i++){
                String key = Thread.currentThread().getName() + String.valueOf(i);
                map.put(key, System.currentTimeMillis());
                ctmap.put(key, System.currentTimeMillis());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ReadThread extends Thread {

        public ReadThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            for (int i = 0; i < 100000; i++) {
                Long wt1time = map.get("wt1" + String.valueOf(i));
                Long wt2time = ctmap.get("wt2" + String.valueOf(i));
                LOGGER.info("wt1 time {}", wt1time);
                LOGGER.info("wt2 time {}", wt2time);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        WriteThread writeThread1 = new WriteThread("wt1");
        WriteThread writeThread2 = new WriteThread("wt2");
        ReadThread readThread = new ReadThread("rd");
        writeThread1.setPriority(8);
        writeThread2.setPriority(9);
        readThread.setPriority(4);
        writeThread1.start();
        writeThread2.start();
        readThread.start();
        writeThread1.join();
        writeThread2.join();
        readThread.join();
        LOGGER.info("map size {}", map.size());
        LOGGER.info("ctmap size {}", ctmap.size());
    }
}
