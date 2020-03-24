package com.panshi.springbootconfig;

import com.panshi.springbootconfig.entity.Person;
import com.panshi.springbootconfig.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringbootConfigApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Object person = applicationContext.getBean(Person.class);
        System.out.println(person);
//        boolean b = applicationContext.containsBean("helloService");
//        HelloService hello = (HelloService) applicationContext.getBean("helloService");
//        System.out.println(b);
//        System.out.println(hello);
        boolean b2 = applicationContext.containsBean("helloService2");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService2");
        System.out.println(b2);
        System.out.println(helloService);

    }

}
