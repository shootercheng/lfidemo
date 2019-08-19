package com.example.demo.bookexample.code3.threadstack.test;

import com.example.demo.bookexample.code3.threadstack.exception.ThreadError;

import java.util.concurrent.*;

/**
 * @author chengdu
 * @date 2019/8/19.
 */
public class ThreadPoolExtException extends ThreadPoolExecutor {

    public ThreadPoolExtException(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                  ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void execute(Runnable runnable){
        super.execute(doRun(runnable, stackErrorLocation()));
    }

    @Override
    public Future<?> submit(Runnable task){
        return super.submit(doRun(task, stackErrorLocation()));
    }

    public Exception stackErrorLocation(){
        return new ThreadError("error stack location");
    }


    private Runnable doRun(Runnable runnable, Exception exception) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                }catch (Exception e){
                    exception.printStackTrace();
                    throw e;
                }
            }
        };
    }
}
