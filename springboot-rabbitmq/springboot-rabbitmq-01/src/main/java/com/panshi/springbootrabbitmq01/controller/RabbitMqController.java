package com.panshi.springbootrabbitmq01.controller;

import com.panshi.springbootrabbitmq01.produce.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RabbitMqController
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  10:17
 * @Version
 */
@RestController
public class RabbitMqController {
    @Autowired
    private RabbitProducer rabbitProducer;

    @GetMapping("/sendDemoQueue")
    public Object sendDemoQueue() {
        rabbitProducer.sendDemoQueue();
        return "success";
    }
    // =====================
    @GetMapping("/sendFanout")
    public Object sendFanout() {
        rabbitProducer.sendFanout();
        return "success";
    }

    // ==========================
    @GetMapping("/sendTopicTopicAB")
    public Object sendTopicTopicAB() {
        rabbitProducer.sendTopicTopicAB();
        return "success";
    }

    @GetMapping("/sendTopicTopicB")
    public Object sendTopicTopicB() {
        rabbitProducer.sendTopicTopicB();
        return "success";
    }

    @GetMapping("/sendTopicTopicBC")
    public Object sendTopicTopicBC() {
        rabbitProducer.sendTopicTopicBC();
        return "success";
    }
}
