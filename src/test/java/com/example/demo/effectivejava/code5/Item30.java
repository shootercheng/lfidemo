package com.example.demo.effectivejava.code5;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Favor generic methods
 * @author chengdu
 * @date 2019/12/22
 */
public class Item30 {

    public Set unionSet(Set set1, Set set2) {
        Set result = new HashSet(set1);
        result.addAll(set2);
        return result;
    }

    public <E> Set<E> unionSetGeneric(Set<E> set1, Set<E> set2) {
        Set<E> result = new HashSet<E>(set1);
        result.addAll(set2);
        return result;
    }

    @Test
    public void testUnionOne() {
        Set set1 = new HashSet();
        set1.add(1);
        set1.add(2);
        Set set2 = new HashSet();
        set2.add(3);
        set2.add(4);
        set2.add("hello world");
        Set result = unionSet(set1, set2);
        System.out.println(result);
        for (Object obj : result) {
            // java.lang.ClassCastException at runtime
            int element = (int) obj;
            System.out.println(element);
        }
    }

    @Test
    public void testGeneric() {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        Set<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        Set<Integer> result = unionSetGeneric(set1, set2);
        for (Integer ele : result) {
            int element = ele;
            System.out.println(element);
        }
    }
}
