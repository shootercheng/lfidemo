package test;


import threadsafe.AtomicBooleanTest;
import threadsafe.VolatileSafeSyncTest;
import threadsafe.VolatileSafeTest;
import threadsafe.VolatileTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengdu
 * @date 2019/8/25.
 */
public class ThreadSafeTest {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public static void main(String[] args){
        for (int i = 0; i < 5; i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
//                    VolatileTest.testVolatile();
//                    AtomicBooleanTest.testAtomic();
//                    VolatileSafeSyncTest.testSyncVolatile();
                    VolatileSafeTest.testSafeVolatile();
                }
            });
        }
    }
}
