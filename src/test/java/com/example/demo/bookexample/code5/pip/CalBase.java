package com.example.demo.bookexample.code5.pip;

import lombok.Data;

/**
 * @author chengdu
 * @date 2019/9/11.
 */
@Data
public class CalBase {

    private int a;
    private int b;
    private volatile int result;

    public CalBase(int a, int b){
        this.a = a;
        this.b = b;
        result = 0;
    }
}
