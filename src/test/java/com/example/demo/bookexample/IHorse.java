package com.example.demo.bookexample;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public interface IHorse {
    void eat();
    default void run(){
        System.out.println("Horse run");
    }
}
