package com.example.demo.bookexample.code5.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author chengdu
 * @date 2019/9/12.
 */
public class ArraySearch {


    public static List<SearchSeq> calSearchSeqs(int arrLength, int threads){
        List<SearchSeq> list = new ArrayList<>(threads);
        int mod = arrLength % threads;
        int step = 0;
        if (mod == 0) {
            step = arrLength / threads;
        } else {
            step = arrLength / threads + 1;
        }
        int lastStep = arrLength - ((threads - 1) * step);
        int start = 0;
        for (int i = 0; i < threads - 1; i++) {
            int end = start + (step - 1);
            SearchSeq searchSeq = new SearchSeq(start, end);
            list.add(searchSeq);
            start = start + step;
        }
        SearchSeq searchSeq = new SearchSeq(start, start + lastStep - 1);
        list.add(searchSeq);
        return list;
    }

    public static void main(String[] args){
        int threads = 3;
        ExecutorService threadPool = Executors.newFixedThreadPool(threads);
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        List<SearchSeq> searchSeqList = calSearchSeqs(arr.length, threads);
        int number = 55;
        SearchTask.setArr(arr);
        List<Future<Integer>> futureList = new ArrayList<>(threads);
        for (int i = 0; i < threads; i++) {
            SearchTask searchTask = new SearchTask(searchSeqList.get(0), number);
            futureList.add(threadPool.submit(searchTask));
        }
        for (Future<Integer> future : futureList){
            try {
                int result = future.get();
                System.out.println(result);
                if (result != -1) {
                    System.out.println(result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }
}
