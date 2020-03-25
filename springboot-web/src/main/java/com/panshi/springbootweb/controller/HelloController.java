package com.panshi.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/index")
    public String index(Model model) {

        model.addAttribute("hello","lisi");
        return "index";
    }
}
