package com.panshi.springbootshiro01.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/26  16:15
 * @Version
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequiresPermissions("user:show")
//    @RequiresPermissions("user:list")
    @RequestMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }
}
