package com.example.demo.bookexample.code3.jdkcollections.list;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author chengdu
 * @date 2019/8/22.
 */
public class LinkedDequeDemo {

    public static void main(String[] args){
       ConcurrentLinkedDeque<String> ctQueue = new ConcurrentLinkedDeque<String>();
       ctQueue.add("1");
       ctQueue.add("2");
       ctQueue.add("3");
       String str = ctQueue.pollFirst();
       ctQueue.remove("2");
       System.out.println(ctQueue.toString());
    }
}
