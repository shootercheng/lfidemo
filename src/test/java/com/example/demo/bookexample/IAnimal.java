package com.example.demo.bookexample;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public interface IAnimal {

    default void breath(){
        System.out.println("animal breath");
    }
}
