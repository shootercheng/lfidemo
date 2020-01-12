package com.example.demo.effectivejava.code5;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * don't use raw types
 * @author chengdu
 * @date 2019/12/21
 */
public class Item26 {

    @Test
    public void testRawType() {
        // error at runtime
        Set rawSet = new HashSet();
        rawSet.add(1);
        rawSet.add("Hello World");
        for (Object obj : rawSet) {
            int i = (int) obj;
            System.out.println(i);
        }
    }

    @Test
    public void testWildCardType() {
        Set<?> wildcardSet = new HashSet<>();
        wildcardSet.add(null);
    }

    @Test
    public void testGenericType() {
        // check type at compile time
        Set<String> genericSet = new HashSet<>();
        genericSet.add("Hello World");
        genericSet.add("hhh");
    }

    public static void main(String[] args) {
    }
}
