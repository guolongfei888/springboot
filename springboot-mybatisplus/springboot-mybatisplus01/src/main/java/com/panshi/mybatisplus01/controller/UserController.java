package com.panshi.mybatisplus01.controller;


import com.panshi.mybatisplus01.entity.User;
import com.panshi.mybatisplus01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuan
 * @since 2020-09-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/selectAll")
    public List<User> selectAll() {
        List<User> userList = userService.selectAll();
        return userList;
    }
}
