package com.act.framework.common.excel.pageexcel;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 *
 * @author : duanzhiqiang
 * @date : 2019-09-05 14:42
 */
public class PageInfoExecutor implements ThreadFactory {
    /**
     * 线程的索引
     */
    private static AtomicInteger index = new AtomicInteger(0);

    /**
     * 线程池
     */
    private static final ExecutorService POOL = new ThreadPoolExecutor(1, 20, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new PageInfoExecutor());


    public static void submit(Runnable runnable) {
        POOL.submit(runnable);
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("pageInfoExecutor_thread_" + index.getAndIncrement());
        return thread;
    }
}
