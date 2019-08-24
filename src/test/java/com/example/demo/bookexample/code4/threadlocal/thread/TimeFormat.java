package com.example.demo.bookexample.code4.threadlocal.thread;

import com.example.demo.bookexample.code4.threadlocal.UnsafeSimplateDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chengdu
 * @date 2019/8/24.
 */
public class TimeFormat extends Thread {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UnsafeSimplateDate.DATE_PATTERN);

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeFormat.class);

    public TimeFormat(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        Date time = null;
        try {
            time = simpleDateFormat.parse("2019/08/24 11:10:57");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LOGGER.info("time {}", simpleDateFormat.format(time));
    }
}
