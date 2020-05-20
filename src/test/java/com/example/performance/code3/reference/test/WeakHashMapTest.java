package com.example.performance.code3.reference.test;

import com.example.performance.code3.reference.model.TestBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * JVM : -Xmx8M
 * @author James
 */
public class WeakHashMapTest {
    private static final int SIZE = 30000;

    /**
     * 内存不够时， GC 回收被没有被引用的数据
     */
    public static void test1() {
        Map<Integer, TestBean> weakHashMap = new WeakHashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            weakHashMap.put(i, new TestBean(Long.valueOf(String.valueOf(i)), "cd" + i));
        }
        System.out.println("end.....");
    }

    public static void test2() {
        Map<Integer, TestBean> weakHashMap = new HashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            weakHashMap.put(i, new TestBean(Long.valueOf(String.valueOf(i)), "cd" + i));
        }
        System.out.println("end.....");
    }

    /**
     * weak hashmap key 被强引用了，无法回收
     */
    public static void test3() {
        Map<Object, TestBean> weakHashMap = new WeakHashMap<>(SIZE);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
            weakHashMap.put(list, new TestBean(Long.valueOf(String.valueOf(i)), "cd" + i));
        }
        System.out.println("end.....");
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }
}
