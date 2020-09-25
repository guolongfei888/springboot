package com.panshi.springbootrabbitmq01;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootRabbitmq01ApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
//      // 创建exchange
//        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//        System.out.println("创建完成");

        // 创建queue
//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

        // 创建绑定规则
//        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpAdmin.exchange","amqp.haha",null));


    }

    @Test
    void contextLoads() {
    }

}
