package com.example.demo.bookexample.code2.synchronizedlock;

/**
 * 优化 ThreadSynTest, 不在 for 循环里面加锁
 * @author chengdu
 * @date 2019/8/7.
 */
public class ThreadRunTest implements Runnable {

    private volatile Integer i;

    public ThreadRunTest(int i){
        this.i = i;
    }

    public Integer getI(){
        return i;
    }

    @Override
    public void run() {
        synchronized (ThreadRunTest.class){
            for (int j = 0; j < 100000; j++){
                i++;
            }
        }
    }
}
