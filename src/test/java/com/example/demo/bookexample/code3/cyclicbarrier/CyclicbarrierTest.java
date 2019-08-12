package com.example.demo.bookexample.code3.cyclicbarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;

/**
 * @author chengdu
 * @date 2019/8/12.
 */
public class CyclicbarrierTest {

    private static Logger LOGGER = LoggerFactory.getLogger(CyclicbarrierTest.class);

    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, new BarrierAction(false, 10));
        Soldier.setCyclic(cyclicBarrier);
        Soldier[] soldiers = new Soldier[10];
        for (int i = 0; i < 10; i++){
            soldiers[i] = new Soldier("soldier " + i);
            soldiers[i].start();
//            if (i == 6){
//                soldiers[6].interrupt();
//            }
        }

        for (int i = 0; i < 10; i++) {
            soldiers[i].join();
        }
        LOGGER.info("child thread over");
    }
}
