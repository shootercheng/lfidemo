package com.example.performance.code3.exception;

import org.junit.Test;

/**
 * @author James
 */
public class ExceptionTest {

    public static void testExceptionTime() {
        long startTime = System.nanoTime();
        int a = 0;
        for (int i = 0; i < 1000000000; i++) {
            try {
                a++;
            } catch (Exception e) {
            }
        }
        System.out.println("time :" + (System.nanoTime() - startTime));
        a = 0;
        startTime = System.nanoTime();
        try {
            for (int i = 0; i < 1000000000; i++) {
                a++;
            }
        } catch (Exception e) {

        }
        System.out.println("time :" + (System.nanoTime() - startTime));
    }

    public static void main(String[] args) {
        testExceptionTime();
    }
}
