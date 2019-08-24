package com.example.demo.bookexample.code4.threadlocal.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author chengdu
 * @date 2019/8/24.
 */
public class RandomThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomThread.class);

    private static final ThreadLocal<Random> randomLocal = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
             return new Random(123);
        }
    };

    private static final int RANDOM_NUM = 10000000;

    private static Random rnd = new Random(100);

    private int mode;

    public RandomThread(String name, int mode){
        super.setName(name);
        this.mode = mode;
    }

    @Override
    public void run(){
        long startTime = System.currentTimeMillis();
        Random random = getRandom();
        for (int i = 0; i < RANDOM_NUM; i++){
            int res = random.nextInt();
//            LOGGER.info("random res {}", res);
        }
        LOGGER.info("thread {} mode {} time {}", this.getName(), mode, (System.currentTimeMillis() - startTime));
    }

    private Random getRandom() {
        if (mode == 1){
            return rnd;
        } else if(mode == 2){
            return randomLocal.get();
        } else {
            return null;
        }
    }
}
