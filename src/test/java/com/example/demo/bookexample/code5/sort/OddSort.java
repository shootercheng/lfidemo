package com.example.demo.bookexample.code5.sort;

import java.util.Arrays;

/**
 * @author chengdu
 * @date 2019/9/14.
 */
public class OddSort {

    public static void sort(Integer[] arr){
        int exchangeFlag = 1;
        int start = 0;
        while (exchangeFlag == 1 || start == 1){
            exchangeFlag = 0;
            for (int i = start; i < arr.length - 1; i = i+ 2){
                if (arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    exchangeFlag = 1;
                }
            }
            System.out.println(Arrays.asList(arr));
            if (start == 0){
                start = 1;
            } else {
                start = 0;
            }
        }
    }

    public static void main(String[] args){
        Integer[] arr = {2,4,6,8,3};
        sort(arr);
    }
}
