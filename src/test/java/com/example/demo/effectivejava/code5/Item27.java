package com.example.demo.effectivejava.code5;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * eliminate unchecked warnings
 * @author chengdu
 * @date 2019/12/21
 */
public class Item27 {

    @Test
    public void testUncheckWarning() {
        Integer[] arr = {1,2,3,4};
        Integer[] result = Arrays.copyOf(arr, 6);
        System.out.println(result.length);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUncheckMap() {
        Map<Integer, Object> map = new HashMap();
        map.put(1, "hello");
        String result = (String) map.get(1);
        System.out.println(result);
    }
}
