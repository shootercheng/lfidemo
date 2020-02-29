package com.example.proxy.service;

/**
 * @author chengdu
 * @date 2019/9/19.
 */
public class CalServiceImpl implements CalService {
    @Override
    public int add(int a, int b) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }
}
