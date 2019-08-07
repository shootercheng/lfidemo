package com.example.demo.bookexample.code2.bugs;

/**
 * @author chengdu
 * @date 2019/8/7.
 */
public class AddOver {

    public static void main(String[] args){
        int a1 = 1234567891;
        int a2 = 1454565767;
        int b = a1+ a2;
        System.out.println(b);
        // 溢出变成负数
        System.out.println(Integer.MAX_VALUE);
    }
}
