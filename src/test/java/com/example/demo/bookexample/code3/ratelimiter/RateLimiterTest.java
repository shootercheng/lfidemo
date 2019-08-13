package com.example.demo.bookexample.code3.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author chengdu
 * @date 2019/8/13.
 */
public class RateLimiterTest {

    private static RateLimiter rateLimiter = RateLimiter.create(4);

    public static void main(String[] args){
        for (int i = 0; i < 50; i++){
            RateThread rateThread = new RateThread("ratethread "+ i);
//            rateLimiter.acquire();
            rateThread.start();
        }
    }
}
