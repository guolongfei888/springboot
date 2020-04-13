package com.panshi.springbootrabbitmq01.produce;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @ClassName RabbitProducer
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  10:11
 * @Version
 */
@Component
public class RabbitProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    // =========================
    public void sendDemoQueue() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("[demoQueue]: "+dateString);
        // 第一个参数为刚刚定义的队列名称
        this.rabbitTemplate.convertAndSend("demoQueue",dateString);
    }

    // =======================
    public void sendFanout() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("[fanout] send msg: "+dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey 我们不用管空着就可以，第三个是你要发送的消息
        this.rabbitTemplate.convertAndSend("fanoutExchange","",dateString);
    }

    // ==================
    public void sendTopicTopicAB() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("[topic.msg] send msg: "+dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey topic.msg，第三个是你要发送的消息
        // 这条信息将会被 topic.a  topic.b接收
        this.rabbitTemplate.convertAndSend("topicExchange","topic.msg",dateString);
    }

    public void sendTopicTopicB() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("[topic.good.msg] send msg: "+dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey topic.msg，第三个是你要发送的消息
        // 这条信息将会被 topic.b接收
        this.rabbitTemplate.convertAndSend("topicExchange","topic.good.msg",dateString);
    }

    public void sendTopicTopicBC() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        dateString = "[topic.m.z] send msg:" + dateString;
        System.out.println(dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey ，第三个是你要发送的消息
        // 这条信息将会被topic.b、topic.c接收
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.m.z", dateString);
    }
}
