package com.example.performance.code3.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author James
 */
public class ListTest {

    /**
     * 指定 ArrayList 大小
     */
    @Test
    public void testCompare() {
        long startTime = System.currentTimeMillis();
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            objectList.add(new Object());
        }
        long listEndTime = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime - startTime) );
        List<Object> objectList1 = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++) {
            objectList1.add(new Object());
        }
        long listEndTime1 = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime1 - listEndTime) );
    }

    @Test
    public void testCompareLinkedListNoData() {
        long startTime = System.currentTimeMillis();
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            objectList.add(new Object());
        }
        long listEndTime = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime - startTime) );
        List<Object> linkedList = new LinkedList<>();
        for (int i = 0; i < 5000000; i++) {
            linkedList.add(new Object());
        }
        long listEndTime1 = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime1 - listEndTime) );
    }

    @Test
    public void testChar() {
        int a = (int) 'A';
        System.out.println(a);
        char sa = (char) (a + 32);
        System.out.println(sa);
        StringBuilder stringBuilder = new StringBuilder();
        int begin = (int) 'A';
        for (int i = 0; i < 26; i++) {
            char c = (char) (begin + i);
            stringBuilder.append("'").append(c).append("'").append(",");
        }
        System.out.println(stringBuilder.toString());
    }

    char[] charArr = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
            'W','X','Y','Z'};

    @Test
    public void randomCharStr() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(26);
            System.out.println(index);
            stringBuilder.append(charArr[index]);
        }
        System.out.println(stringBuilder);
    }

    public String genRandomChar(Random random, int len) {
        StringBuilder stringBuilder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(26);
            stringBuilder.append(charArr[index]);
        }
        return stringBuilder.toString();
    }

    @Test
    public void testCompareLinkedListSameData() {
        String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        long startTime = System.currentTimeMillis();
        List<String> objectList = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            objectList.add(data);
        }
        long listEndTime = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime - startTime) );
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 5000000; i++) {
            linkedList.add(data);
        }
        long listEndTime1 = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime1 - listEndTime) );
    }

    @Test
    public void testCompareLinkedListData() {
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        List<String> objectList = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            objectList.add(genRandomChar(random, 100));
        }
        long listEndTime = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime - startTime) );
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 5000000; i++) {
            linkedList.add(genRandomChar(random, 100));
        }
        long listEndTime1 = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime1 - listEndTime) );
    }

    @Test
    public void testCompareLinkedListDataOrder() {
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 5000000; i++) {
            linkedList.add(genRandomChar(random, 100));
        }
        long listEndTime = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime - startTime) );
        List<String> objectList = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            objectList.add(genRandomChar(random, 100));
        }
        long listEndTime1 = System.currentTimeMillis();
        System.out.println(" list time : " + (listEndTime1 - listEndTime) );
    }

    @Test
    public void testCompareLinkedTravrse() {
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        int size = 50000;
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            linkedList.add(genRandomChar(random, 100));
        }
        long listEndTime = System.currentTimeMillis();
        System.out.println("link list time : " + (listEndTime - startTime) );
        List<String> objectList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            objectList.add(genRandomChar(random, 100));
        }
        long listEndTime1 = System.currentTimeMillis();
        System.out.println("array list time : " + (listEndTime1 - listEndTime) );
        // link list
        startTime = System.currentTimeMillis();
        String temp;
        for (int i = 0; i < size; i++) {
            temp = linkedList.get(i);
        }
        System.out.println("link list get time :" + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        for (String s : linkedList) {
            temp = s;
        }
        System.out.println("link list foreach time :" + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
       for (int i = 0; i < size; i++) {
            temp = objectList.get(i);
        }
        System.out.println("array list get time :" + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        for (String s : objectList) {
            temp = s;
        }
        System.out.println("array list foreach time :" + (System.currentTimeMillis() - startTime));
    }
}
