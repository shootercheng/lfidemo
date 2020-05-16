package com.example.performance.code3.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author James
 */
public class MapTest {

    @Test
    public void testHashMapNull() {
        Map<String, Integer> map = new HashMap<>();
        map.put(null, 1);
        map.put("key", null);
        System.out.println(map);
    }

    @Test
    public void testHashTable() {
        Map<String, Integer> table = new Hashtable<>();
        try {
            table.put(null, 1);
            Assert.fail("--------------bug----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            table.put("1", null);
            Assert.fail("--------------bug----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHashMapKey() {
        long startNanoTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            int hashCode = hash(i);
        }
        System.out.println("time " + (System.nanoTime() - startNanoTime) + "ns");
    }

    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
