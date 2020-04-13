package com.panshi.springbootshirojpa.controller;

import com.panshi.springbootshirojpa.model.Role;
import com.panshi.springbootshirojpa.model.User;
import com.panshi.springbootshirojpa.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginControllelr
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  13:54
 * @Version
 */
@RestController
public class LoginControllelr {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // 添加用户认证信息
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassword());
        // 进行验证，这里可以捕获异常，然后返回对应信息
        SecurityUtils.getSubject().login(token);
        return "login ok!";
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping(value = "/addUser")
    public String addUser(@RequestBody User user) {
        user = loginService.addUser(user);
        return "addUser is ok! \n" + user;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping(value = "/addRole")
    public String addRole(@RequestBody Role role) {
        role = loginService.addRole(role);
        return "addRole is ok! \n" + role;
    }

    /**
     * 注解的使用
     * @return
     */
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @GetMapping("/create")
    public String create() {
        return "Create success!";
    }

    @GetMapping("/index")
    public String index() {
        return "index page!";
    }

    @GetMapping("/error")
    public String error() {
        return "error page!";
    }

    /**
     * 退出的时候是get请求，主要是用于退出
     * @return
     */
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "logout";
    }
}
