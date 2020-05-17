package com.example.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class BatchInsertUtil {

    public static <T> int batchInsert(List<T> inputList, Function<List<T>, Integer> function, int batchNum) {
        if (CollectionUtils.isEmpty(inputList)) {
            return 0;
        }
        int listSize = inputList.size();
        if (listSize <= batchNum) {
            return function.apply(inputList);
        }
        int sum = 0;
        int startIndex = 0;
        while (startIndex + batchNum < listSize) {
            List<T> batchList = inputList.subList(startIndex, startIndex + batchNum);
            sum = sum + function.apply(batchList);
            startIndex = startIndex + batchNum;
        }
        List<T> leftList = inputList.subList(startIndex, listSize);
        sum = sum + function.apply(leftList);
        return sum;
    }

    /**
     * 在循环中插入
     * @param inputList
     * @param function
     * @param batchNum
     * @param <T>
     * @return
     */
    public static <T> int batchInsertInLoop(List<T> inputList, Function<List<T>, Integer> function, int batchNum) {
        if (CollectionUtils.isEmpty(inputList)) {
            return 0;
        }
        int listSize = inputList.size();
        if (listSize == batchNum) {
            int sum = function.apply(inputList);
            inputList.clear();
            return sum;
        }
        return 0;
    }
}
