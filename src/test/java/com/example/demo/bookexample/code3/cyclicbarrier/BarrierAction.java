package com.example.demo.bookexample.code3.cyclicbarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengdu
 * @date 2019/8/12.
 */
public class BarrierAction implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarrierAction.class);

    private boolean flag;

    private int n;

    public BarrierAction(boolean flag, int n){
        this.flag = flag;
        this.n = n;
    }

    @Override
    public void run() {
        if (flag){
            LOGGER.info("司令: [士兵 {} ]个 任务完成", n);
        } else {
            LOGGER.info("司令: [士兵 {} ]个 集合完毕", n);
            flag = true;
        }
    }
}
