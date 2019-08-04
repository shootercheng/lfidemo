package com.example.demo.bookexample;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class Code61 {

    @Test
    public void testArr(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i = 0; i < arr.length; i++){
            System.out.println(i);
        }
    }

    @Test
    public void testArrStream(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        Arrays.stream(arr).forEach(System.out::println);
    }

    @Test
    public void testArrMap(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        Arrays.stream(arr).map(a -> a+1);
        Arrays.stream(arr).forEach(System.out::println);
    }

    @Test
    public void filterByCondition(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i = 0; i < arr.length; i++){
            if (arr[i] % 2 == 0){
                System.out.println(arr[i]);
            }
        }
        System.out.println("-------------------------------------");
        Arrays.stream(arr).map(a -> (a % 2 == 0 ? a : a + 1)).forEach(System.out::println);
    }

    public static boolean isEven(int number){
        if (number % 2 == 0){
            return true;
        }
        return false;
    }

    @Test
    public void testSqrt(){
       double res = Math.sqrt(4);
       System.out.println(res);
       IntPredicate intPredicate = Code61::isEven;
       IntConsumer intConsumer = System.out::println;
//       IntStream.range(0, 100000).filter(intPredicate).forEach(intConsumer);
       IntStream.range(0, 100000).parallel().filter(intPredicate).forEach(intConsumer);
    }
}
