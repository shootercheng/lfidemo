package com.example.demo.bookexample.code2.synchronizedlock;

/**
 * @author chengdu
 * @date 2019/8/7.
 */
public class ThreadSynTest extends Thread {

    public ThreadSynTest(String name){
        super.setName(name);
    }

    private static int i;

    public static synchronized void addI(){
        i++;
    }

    public static int getI(){
        return i;
    }

    @Override
    public void run(){
        for (int i=0; i < 100000; i++){
            addI();
        }
    }
}
