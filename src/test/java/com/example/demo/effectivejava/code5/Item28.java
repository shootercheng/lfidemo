package com.example.demo.effectivejava.code5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Prefer lists to arrays
 * @author chengdu
 * @date 2019/12/21
 */
public class Item28 {

    @Test
    public void testObjArr() {
        // error at runtime
        Object[] objects = new Long[1];
        objects[0] = "hello world";
    }

    @Test
    public void testGenericList() {
        List<Long> longList = new ArrayList<>();
        // error at compile time
//        longList.add("1233");
        longList.add(1L);
    }

    /**
     * cast will fail at runtime if you get the
     * type wrong
     */
    class ChooserOne {
        private final Object[] choiceArray;

        private ChooserOne(Collection choices) {
            choiceArray = choices.toArray();
        }

        public Object choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceArray[rnd.nextInt(choiceArray.length)];
        }
    }

    class ChooserTwo<T> {
        private final T[] choiceArray;

        // warning unchecked cast
        private ChooserTwo(Collection<T> choices) {
            choiceArray = (T[]) choices.toArray();
        }

        public T choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceArray[rnd.nextInt(choiceArray.length)];
        }
    }

    class ChooserThree<T> {
        private final List<T> choiceList;

        private ChooserThree(List<T> choiceList) {
            this.choiceList = choiceList;
        }

        public T choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceList.get(rnd.nextInt(choiceList.size()));
        }
    }

    @Test
    public void testChooserOne() {
        List list = new ArrayList();
        list.add(1);
        list.add("hello world");
        list.add("hello world2");
        ChooserOne chooserOne = new ChooserOne(list);
        // maybe error at runtime, ClassCastException
        int result = (int) chooserOne.choose();
        System.out.println(result);
    }

    @Test
    public void testChooserTwo() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        ChooserTwo<Integer> chooserTwo = new ChooserTwo<>(list);
        int result = chooserTwo.choose();
        System.out.println(result);
    }

    @Test
    public void testChooserThree() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        ChooserThree<Integer> chooserThree = new ChooserThree<>(list);
        int result = chooserThree.choose();
        System.out.println(result);
    }
}
