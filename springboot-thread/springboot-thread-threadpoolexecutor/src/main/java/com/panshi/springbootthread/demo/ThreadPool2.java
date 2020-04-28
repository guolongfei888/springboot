package com.panshi.springbootthread.demo;

import java.util.concurrent.*;

/**
 * 拒绝策略
 * 1、AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作；
 *
 * 2、CallerRunsPolicy策略：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行；
 *
 * 3、DiscardOledestPolicy策略：该策略会丢弃任务队列中最老的一个任务，也就是当前任务队列中最先被添加进去的，马上要被执行的那个任务，并尝试再次提交；
 *
 * 4、DiscardPolicy策略：该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失；
 */
public class ThreadPool2 {
    private static ExecutorService pool;

    public static void main(String[] args) {
        // 自定义拒绝策略
//        pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5),
//                Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
//            @Override
//            public void rejectedExecution(Runnable r, ThreadPoolExecutor threadPoolExecutor) {
//                System.out.println(r.toString()+"执行了拒绝策略");
//            }
//        });
//        for (int i=0;i<10;i++) {
//            pool.execute(new ThreadTask3());
//        }

        // 自定义线程工厂
        pool = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable runnable) {
                        System.out.println("线程 "+runnable.hashCode()+"创建");
                        // 线程命名
                        Thread th = new Thread(runnable,"threadPool"+runnable.hashCode());
                        return th;
                    }
                },new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i=0;i<10;i++) {
            pool.execute(new ThreadTask3());
        }
    }


    static class ThreadTask3 implements Runnable {
        @Override
        public void run() {
            try {
                //让线程阻塞，使后续任务进入缓存队列
                Thread.sleep(1000);
                System.out.println("ThreadName:"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
