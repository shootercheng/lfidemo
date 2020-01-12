package com.example.demo.effectivejava.code5;

import java.util.ArrayList;
import java.util.List;

/**
 * Use bounded wildcards to increase API flexibility
 * @author chengdu
 * @date 2019/12/21
 */
public class Item31<E> {

    public void printObjList(List list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    public void printList(List<E> list) {
        for (E e : list) {
            System.out.println(e);
        }
    }

    // some subtype of E
    // wildcard type for parameter that serves as an E producer
    public void printBoundWild(List<? extends E> list) {
        for (E e : list) {
            System.out.println(e);
        }
    }

    // some supertype of E
    // wildcard type for parameter that serves as an E consumer43
    public void addConsumerList(List<? super E> list, E e) {
        list.add(e);
    }

    public static void main(String[] args) {
        Item31<Number> item31 = new Item31<>();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        item31.printObjList(integerList);
        // check error at compile time
//        item31.printList(integerList);
        item31.printBoundWild(integerList);

        System.out.println("----------consumer---------------");
        Item31<Integer> integerItem31 = new Item31<>();
        List<Number> numberList = new ArrayList<>();
        integerItem31.addConsumerList(numberList, 1);
        integerItem31.addConsumerList(numberList, 2);
        System.out.println(numberList);
    }
}
