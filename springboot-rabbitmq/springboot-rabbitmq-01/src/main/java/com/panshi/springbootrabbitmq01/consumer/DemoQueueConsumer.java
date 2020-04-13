package com.panshi.springbootrabbitmq01.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName DemoQueueConsumer
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  10:15
 * @Version
 */
@Component
@RabbitListener(queues = "demoQueue")
public class DemoQueueConsumer {
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void revieved(String msg) {
        System.out.println("[demoQueue] recieved message: "+msg);
    }
}
