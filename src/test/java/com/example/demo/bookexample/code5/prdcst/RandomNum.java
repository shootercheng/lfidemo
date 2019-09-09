package com.example.demo.bookexample.code5.prdcst;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author chengdu
 * @date 2019/9/9.
 */
public class RandomNum {

    private static class RandomHolder {
        private static final SecureRandom random = new SecureRandom();
    }

    public static int genRandomInteger(int bound){
        return RandomHolder.random.nextInt(bound);
    }
}
