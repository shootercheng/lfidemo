package com.example.demo.bookexample.code2.bugs;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengdu
 * @date 2019/8/7.
 */
public class CollectionTest {

    private static  List<Integer> list = new ArrayList<>();

    private static  Vector<Integer> vectorList = new Vector<>();

    private static  Map<String, Integer> map = new HashMap<>();

    private static  Map<String, Integer> currentHashMap = new ConcurrentHashMap<>();

    private static class ThreadTest extends Thread {

        public ThreadTest(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
//                vectorList.add(i);
            }
        }
    }

    private static class ThreadMapTest extends Thread {

        public ThreadMapTest(String name) {
            super.setName(name);
        }

        @Override
        public void run(){
            for (int i = 0; i < 10000; i++){
                map.put(getName() + i, i);
                currentHashMap.put(getName() + i, i);
            }
        }
    }

    public static void testList() throws InterruptedException {
        ThreadTest threadTest1 = new ThreadTest("thread test 1");
        ThreadTest threadTest2 = new ThreadTest("thread test 2");
        threadTest1.start();
        threadTest2.start();
        threadTest1.join();
        threadTest2.join();
        System.out.println(list.size());
        System.out.println(vectorList.size());
    }

    public static void testMap() throws InterruptedException {
        ThreadMapTest threadMapTest1 = new ThreadMapTest("map 1 test");
        ThreadMapTest threadMapTest2 = new ThreadMapTest("map 2 test");
        threadMapTest1.start();
        threadMapTest2.start();
        threadMapTest1.join();
        threadMapTest2.join();
        System.out.println(map.size());
        System.out.println(currentHashMap.size());
    }

    public static void main(String[] args) throws InterruptedException {
//        testList();
        testMap();
    }
}
