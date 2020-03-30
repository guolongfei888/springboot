package com.panshi.springbootdatajpa.controller;

import com.panshi.springbootdatajpa.entity.User;
import com.panshi.springbootdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @ClassName UserController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  20:18
 * @Version
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
    
    @GetMapping("/user")
    public User insertUser(User user) {
        User save = userRepository.save(user);
        return save;
    }
    
}
