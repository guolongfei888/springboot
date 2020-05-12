package com.panshi.springbootfilter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description
 * @Author guolongfei
 * @Date 2020/5/12  15:34
 * @Version
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "hello world";
    }
}
