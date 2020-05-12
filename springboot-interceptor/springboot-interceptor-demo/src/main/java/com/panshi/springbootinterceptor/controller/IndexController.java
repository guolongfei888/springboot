package com.panshi.springbootinterceptor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description
 * @Author guolongfei
 * @Date 2020/5/12  16:39
 * @Version
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index () {
        System.out.println("index");
        return "hello World";
    }
}
