package com.example.demo.bookexample.code2.stop;

import java.util.UUID;

/**
 * @author chengdu
 * @date 2019/8/4.
 */
public class StopThreadUnsafe {

    private static User u = new User();

    private static class ChangeObjectThread extends Thread {

        @Override
        public void run(){
            while (true){
                synchronized (u){
                    int time = (int) System.currentTimeMillis() / 1000;
                    u.setId(time);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(time));
                }
                Thread.yield();
            }
        }
    }

    private static class SafeStopChangeObjectThread extends Thread {

        volatile boolean stop = false;

        public void stopThread(){
            stop = true;
        }

        @Override
        public void run(){
            while (true){
                synchronized (u){
                    if (stop){
                        break;
                    }
                    int time = (int) System.currentTimeMillis() / 1000;
                    u.setId(time);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(time));
                }
                Thread.yield();
            }
        }
    }

    private static class ReadObjectThread extends Thread {

        @Override
        public void run(){
            while (true){
                synchronized (u){
                    if (! String.valueOf(u.getId()).equals(u.getName())){
                        System.out.println(u);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new ReadObjectThread().start();
        while (true) {
//            ChangeObjectThread changeObjectThread = new ChangeObjectThread();
            SafeStopChangeObjectThread changeObjectThread = new SafeStopChangeObjectThread();
            changeObjectThread.start();
            Thread.sleep(100);
//            changeObjectThread.stop();
            changeObjectThread.stopThread();
        }
    }
}
