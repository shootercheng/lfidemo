package com.example.demo.bookexample.code3.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author chengdu
 * @date 2019/8/21.
 */
public class CountTask extends RecursiveTask<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountTask.class);

    private long start;

    private long end;

    private static int THRESHOLD = 10;

    public static void setTHRESHOLD(int threshold){
        CountTask.THRESHOLD = threshold;
    }

    public CountTask(long start, long end){
        this.start = start;
        this.end = end;
        LOGGER.info("count task start {} end {}", start, end);
    }

    @Override
    protected Long compute() {
        long result = 0L;
        if ((end - start) <= THRESHOLD){
            for (long i = start; i <= end; i++){
                result = result + i;
            }
        } else {
            long mod = (end - start) % THRESHOLD;
            long childTasks;
            if (mod == 0){
                childTasks = ( end - start) / THRESHOLD;
            } else {
                childTasks = (end - start) / THRESHOLD + 1;
            }
            CountTask[] countTaskList = createCountTask(childTasks);
            for (CountTask countTask : countTaskList){
                result = result + countTask.join();
            }
        }
        return result;
    }

    private CountTask[] createCountTask(long childTasks) {
        int size = Integer.valueOf(String.valueOf(childTasks));
        CountTask[] countTasks = new CountTask[size];
        long startTemp = start;
        for (int i = 0; i < childTasks - 1; i++){
            CountTask countTask = new CountTask(startTemp, startTemp + THRESHOLD - 1);
            countTasks[i] = countTask;
            countTask.fork();
            startTemp = startTemp + THRESHOLD;
        }
        // 最后一个计算任务
        CountTask countTask = new CountTask(startTemp, end);
        countTasks[size - 1] = countTask;
        countTask.fork();
        return countTasks;
    }
}
