package com.example.demo.bookexample.code5.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @author chengdu
 * @date 2019/9/12.
 */
public class SearchTask implements Callable<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTask.class);

    private static volatile int index = -1;

    private static int[] arr;

    public static void setArr(int[] arr) {
        SearchTask.arr = arr;
    }

    private SearchSeq searchSeq;

    private int number;

    public SearchTask(SearchSeq searchSeq, int number){
        this.searchSeq = searchSeq;
        this.number = number;
    }

    public int searchArr(){
        if (index != -1){
            return index;
        }
        int start = searchSeq.getStart();
        int end = searchSeq.getEnd();
        for (int i = start; i <= end; i++){
            if (arr[i] == number){
                index = i;
            }
        }
        return index;
    }

    @Override
    public Integer call() throws Exception {
        int result = searchArr();
        LOGGER.info("SearchSeq {} result {}", searchSeq, result);
        return result;
    }
}
