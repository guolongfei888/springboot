package com.panshi.springbootthread.demo;

import java.util.concurrent.*;

/**
 * 四、ThreadPoolExecutor扩展
 *
 * ThreadPoolExecutor扩展主要是围绕beforeExecute()、afterExecute()和terminated()三个接口实现的，
 *
 * 1、beforeExecute：线程池中任务运行前执行
 *
 * 2、afterExecute：线程池中任务运行完毕后执行
 *
 * 3、terminated：线程池退出后执行
 */
public class ThreadPool3 {
    private static ExecutorService pool;

    public static void main(String[] args) {
        //实现自定义接口
        pool = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        System.out.println("线程"+r.hashCode()+"创建");
                        //线程命名
                        Thread th = new Thread(r,"threadPool"+r.hashCode());
                        return th;
                    }
                },new ThreadPoolExecutor.CallerRunsPolicy()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                System.out.println("准备执行："+ ((ThreadTask4)r).getTaskName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                System.out.println("执行完毕："+((ThreadTask4)r).getTaskName());
            }

            @Override
            protected void terminated() {
                super.terminated();
                System.out.println("线程池退出");
            }
        };
        for (int i=0;i<10;i++) {
            pool.execute(new ThreadTask4("Task "+i));
        }
        pool.shutdown();
    }

    static class ThreadTask4 implements Runnable {
        private String taskName;

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public ThreadTask4(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            //输出执行线程的名称
            System.out.println("TaskName"+this.getTaskName()+"---ThreadName:"+Thread.currentThread().getName());
        }
    }
}
