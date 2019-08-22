package com.example.demo.bookexample.code3.jdkcollections.list;

import com.example.demo.bookexample.code3.jdkcollections.map.ConcurrentHashMapTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author chengdu
 * @date 2019/8/22.
 */
public class ConcurrentLinkedDequeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentHashMapTest.class);

    private static List<String> list = new ArrayList<String>();

    private static ConcurrentLinkedDeque<String> ctlist = new ConcurrentLinkedDeque<String>();

    private static class WriteThread extends Thread{

        public WriteThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            for (int i = 0; i < 100000; i++){
                String key = Thread.currentThread().getName() + String.valueOf(i);
                list.add(key);
                ctlist.add(key);
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
                String value1 = list.get(i);
                String value2 = ctlist.poll();
                LOGGER.info("wt1 time {}", value1);
                LOGGER.info("wt2 time {}", value2);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
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
        LOGGER.info("map size {}", list.size());
        LOGGER.info("ctmap size {}", ctlist.size());
    }
}
