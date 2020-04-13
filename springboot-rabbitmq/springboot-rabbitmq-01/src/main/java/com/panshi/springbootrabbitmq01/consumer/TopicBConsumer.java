package com.panshi.springbootrabbitmq01.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicAConsumer
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  10:52
 * @Version
 */
@Component
@RabbitListener(queues = "topic.b")
public class TopicBConsumer {
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(String msg) {
        System.out.println("[topic.b] recieved message:" + msg);
    }
}
