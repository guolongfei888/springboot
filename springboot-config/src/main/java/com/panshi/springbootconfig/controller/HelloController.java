package com.panshi.springbootconfig.controller;

import com.panshi.springbootconfig.entity.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName HelloController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/24  14:04
 * @Version
 */
@RestController
public class HelloController {
    @Resource
    private Person person;

    @RequestMapping("/hello")
    public String hello() {
//        return "hello World!!!";
        return person.toString();
    }
}
