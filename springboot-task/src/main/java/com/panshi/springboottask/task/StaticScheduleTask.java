package com.panshi.springboottask.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 使用SpringBoot创建定时任务非常简单，目前主要有以下三种创建方式：

 一、基于注解(@Scheduled)
 二、基于接口（SchedulingConfigurer） 前者相信大家都很熟悉，但是实际使用中我们往往想从数据库中读取指定时间来动态执行定时任务，这时候基于接口的定时任务就派上用场了。
 三、基于注解设定多线程定时任务
 */
//@EnableScheduling  // 开启定时任务
//@Configuration //主要用于标记配置类，兼备Component的效果
public class StaticScheduleTask {

//    @Scheduled(cron = "0/5 * * * * ?")  // 到达设定时间执行
//    @Scheduled(fixedDelay = 5000)     // 每次任务执行完5秒后继续执行
//    @Scheduled(fixedRate = 5000)    // 每次任务开始执行5秒后继续执行
    public void configureTasks() {
        System.out.println("执行静态定时任务时间:"+ LocalDateTime.now());
    }

}
