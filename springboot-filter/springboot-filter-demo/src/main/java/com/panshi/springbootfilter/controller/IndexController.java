package com.panshi.springbootfilter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description
 * @Author guolongfei
 * @Date 2020/5/12  15:22
 * @Version
 */
@RestController
public class IndexController {
    @RequestMapping("/1/index")
    public String index() {
        return "hello world1";
    }

    @RequestMapping("/2/index")
    public String index2() {
        return "hello world2";
    }
}
