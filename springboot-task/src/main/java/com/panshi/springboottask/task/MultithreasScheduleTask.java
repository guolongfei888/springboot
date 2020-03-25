package com.panshi.springboottask.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName MultithreasScheduleTask
 * @Description
 * @Author guolongfei
 * @Date 2020/3/3  10:28
 * @Version
 */
@Component
@EnableScheduling   // 开启定时任务
@EnableAsync    // 开启多线程
public class MultithreasScheduleTask {
    @Async
    @Scheduled(fixedDelay = 1000)  // 间隔1秒
    public void first() throws InterruptedException {
        System.out.println("第一个定时任务:"+ LocalDateTime.now().toLocalTime()+"\r\n线程:"+Thread.currentThread().getName());
        System.out.println();
        Thread.sleep(1000*10);
    }

    @Async
    @Scheduled(fixedDelay = 2000)
    public void second() {
        System.out.println("第二个定时任务:"+ LocalDateTime.now().toLocalTime()+"\r\n线程:"+Thread.currentThread().getName());
        System.out.println();
    }

}