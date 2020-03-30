package com.panshi.springbootweb.controller;

import com.panshi.springbootweb.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
public class IndexController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user) {
        if ("aaa".equals(user)) {
            throw new UserNotExistException();
        }
        return "hello World";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map) {
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
//        model.addAttribute("hello","lisi");
//        return "index";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,String> map, HttpSession session) {
        if (username != null && !"".equals(username) && "123456".equals(password)) {
            // 登录成功,防止表单重复提交   重定向
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        } else {
            map.put("msg","用户名或密码不对");
            return "login";
        }
    }
}
