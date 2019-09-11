package com.example.demo.bookexample.code5.pip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengdu
 * @date 2019/9/11.
 */
public class PipMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(PipMain.class);

    public static void main(String[] args) throws InterruptedException {
        //   (a * b + a) / b
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++){
            int result = (i * i + i) / i;
            LOGGER.info("result {}", result);
        }
        long endTime = System.currentTimeMillis();
        long singleTime = endTime -startTime;

        //   (a * b + a) / b;
        Multipy multipy = new Multipy("multipy", 100000);
        Plus plus = new Plus("plus", 100000);
        Div div = new Div("div", 100000);
        multipy.start();
        plus.start();
        div.start();
        List<CalBase> calBaseList = new ArrayList<>(100000);
        for (int i = 1; i <= 100000; i++){
            CalBase calBase = new CalBase(i, i);
            Multipy.addCalBase(calBase);
            calBaseList.add(calBase);
        }
        multipy.join();
        plus.join();
        div.join();
        System.out.println(calBaseList.size());
        long multiTime = System.currentTimeMillis() - endTime;
        LOGGER.info("singlethread time {},pip thread time {}", singleTime, multiTime);
    }
}
