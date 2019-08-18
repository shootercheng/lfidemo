package com.example.demo.bookexample.code3.threadpool.rejecthandlers;

import com.example.demo.bookexample.code3.threadpool.thread.TestThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author chengdu
 * @date 2019/8/16.
 */
public class RejectThreadPoolTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RejectThreadPoolTest.class);

    private static ExecutorService createExecutorService(){
        ExecutorService executorService = new ThreadPoolExecutor(5,
        5,
        4000,
        TimeUnit.MILLISECONDS,
        new LinkedBlockingDeque<Runnable>(),
        Executors.defaultThreadFactory(),
        new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                LOGGER.info("Thread {} is discard", r.toString());
            }
        });
        return executorService;
    }

    private static ExecutorService createArrayBlockingPool(){
        ExecutorService executorService = new ThreadPoolExecutor(5,
                8,
                4000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        LOGGER.info("Thread {} is discard", r.toString());
                    }
                });
        return executorService;
    }

    private static ExecutorService createRejectPool(){
        ExecutorService executorService = new ThreadPoolExecutor(5,
                8,
                4000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        return executorService;
    }

    public static void main(String[] args) throws InterruptedException {
//         ExecutorService executorService = createExecutorService();
         ExecutorService executorService = createArrayBlockingPool();
//        ExecutorService executorService = createRejectPool();
        for (int i = 0; i < 20; i++){
            TestThread threadTask = new TestThread("task" + i);
            executorService.submit(threadTask);
        }
        Thread.sleep(1000);
        executorService.shutdown();
    }
}
