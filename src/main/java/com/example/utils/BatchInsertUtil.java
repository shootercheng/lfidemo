package com.example.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;

/**
 * @author chengdu
 * @date 2020/1/12
 */
public class BatchInsertUtil {

    public static <T> int batchInsert(List<T> intputList, Function<List<T>, Integer> function, int batchNum) {
        if (CollectionUtils.isEmpty(intputList)) {
            return 0;
        }
        int listSize = intputList.size();
        if (listSize < batchNum) {
            function.apply(intputList);
            return listSize;
        }
        int sum = 0;
        int startIndex = 0;
        while (startIndex + batchNum < listSize) {
            List<T> batchList = intputList.subList(startIndex, startIndex + batchNum);
            sum = sum + function.apply(batchList);
            startIndex = startIndex + batchNum;
        }
        if (startIndex < listSize) {
            List<T> leftList = intputList.subList(startIndex, listSize);
            sum = sum + function.apply(leftList);
        }
        return sum;
    }
}
