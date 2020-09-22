package com.panshi.security02.controller;

import com.panshi.security02.entity.SysUser;
import com.panshi.security02.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * @Auther: guo
 * @Date: 10:57 2020/9/21
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService userService;

    @RequestMapping("/")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
        return "home";
    }
    @RequestMapping("/add")
    public String add() {
        return "add";
    }

    @Transactional
    @PostMapping("/insert")
    public String insertUser( SysUser user) {
        String password =  user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);
        userService.insertUser(user);
        System.out.println(user.toString());
        return "home";

    }
    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

    @RequestMapping("/role")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_TOURISTS')")
    public String printRole() {
        return "如果你看见这句话，说明你有ROLE_TOURISTS角色";
    }
}
