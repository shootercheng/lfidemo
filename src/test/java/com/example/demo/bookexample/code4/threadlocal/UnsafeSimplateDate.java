package com.example.demo.bookexample.code4.threadlocal;

import com.example.demo.bookexample.code4.threadlocal.thread.TimeFormat;
import com.example.demo.bookexample.code4.threadlocal.thread.TimeFormatThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengdu
 * @date 2019/8/24.
 */
public class UnsafeSimplateDate {

    public static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

    private static final Logger LOGGER = LoggerFactory.getLogger(UnsafeSimplateDate.class);

    public static void testError(){
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++){
            TimeFormat timeFormat = new TimeFormat("time " + i);
            threadPool.execute(timeFormat);
        }
        threadPool.shutdown();
    }

    public static void test(){
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
                    Date time = null;
                    try {
                        time = simpleDateFormat.parse("2019/08/24 11:10:57");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    LOGGER.info("time {}", simpleDateFormat.format(time));
                }
            });
        }
        threadPool.shutdown();
    }

    public static void testTimeThreadLocal(){
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++){
            TimeFormatThreadLocal timeFormatThreadLocal = new TimeFormatThreadLocal("time threadlocal " + i);
            threadPool.execute(timeFormatThreadLocal);
        }
        threadPool.shutdown();
    }

    public static void main(String[] args){
//        testError();
//        test();
        testTimeThreadLocal();
    }

}
