package com.panshi.startertest.controller;

import com.panshi.autoconfigurer.config.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @Auther: guo
 * @Date: 16:16 2020/9/25
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        return  helloService.sayHellSuperbeyone("aaa");
    }
}
