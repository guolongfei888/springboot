package com.panshi.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HelloController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/25  11:54
 * @Version
 */
@Controller
public class HelloController {

    @RequestMapping("/success")
    public String success(Map<String,Object> map) {
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
//        model.addAttribute("hello","lisi");
//        return "index";
    }
}
