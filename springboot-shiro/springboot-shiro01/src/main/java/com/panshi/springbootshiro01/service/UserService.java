package com.panshi.springbootshiro01.service;

import com.panshi.springbootshiro01.entity.User;

/**
 * @ClassName UserService
 * @Description
 * @Author guolongfei
 * @Date 2020/3/26  17:19
 * @Version
 */
public interface UserService {
    int insert(User user);

    User selectByUsername(String username);
}
