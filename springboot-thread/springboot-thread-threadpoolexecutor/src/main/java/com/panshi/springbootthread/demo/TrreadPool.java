package com.panshi.springbootthread.demo;

import java.util.concurrent.*;

/**
 *corePoolSize:指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列中去；
 *
 * maximumPoolSize:指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
 *
 * keepAliveTime:当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
 *
 * unit:keepAliveTime的单位
 *
 * workQueue:任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；
 *
 * threadFactory:线程工厂，用于创建线程，一般用默认即可；
 *
 * handler:拒绝策略；当任务太多来不及处理时，如何拒绝任务；
 */
public class TrreadPool {
    /**
     * workQueue任务队列:  它一般分为SynchronousQueue 直接提交队列、ArrayBlockingQueue 有界任务队列、
     *                              LinkedBlockingQueue 无界任务队列、PriorityBlockingQueue 优先任务队列；
     *
     */
    private static ExecutorService pool;

    public static void main(String[] args) {
        //maximumPoolSize设置为2 ，拒绝策略为AbortPolic策略，直接抛出异常
        // SynchronousQueue 直接提交队列
//        pool = new ThreadPoolExecutor(1,2,1000, TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>(),
//                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        // ArrayBlockingQueue 有界任务队列
//        pool = new ThreadPoolExecutor(1,2,1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),
//                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        // LinkedBlockingQueue 无界任务队列
//        pool = new ThreadPoolExecutor(1,2,1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
//                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

//        for (int i=0;i<14;i++) {
//            pool.execute(new ThreadTask());
//        }
        // PriorityBlockingQueue 优先任务队列
        pool = new ThreadPoolExecutor(1,2,1000, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i=0;i<10;i++) {
            pool.execute(new ThreadTask2(i));
        }
    }

}
class ThreadTask implements Runnable {

    public ThreadTask() {

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}


class ThreadTask2 implements Runnable,Comparable<ThreadTask2> {
    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ThreadTask2(int priority) {
        this.priority = priority;
    }

    //当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1,值越小优先级越高
    @Override
    public int compareTo(ThreadTask2 o) {
        return this.priority>o.priority?-1:1;
    }

    @Override
    public void run() {
        try {
            //让线程阻塞，使后续任务进入缓存队列
            Thread.sleep(1000);
            System.out.println("priority:"+this.priority+",ThreadName:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}