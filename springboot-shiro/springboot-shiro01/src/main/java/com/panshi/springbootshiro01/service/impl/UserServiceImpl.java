package com.panshi.springbootshiro01.service.impl;

import com.panshi.springbootshiro01.entity.User;
import com.panshi.springbootshiro01.mapper.UserMapper;
import com.panshi.springbootshiro01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author guolongfei
 * @Date 2020/3/26  17:19
 * @Version
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        int count = userMapper.insert(user);
        return count;
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
