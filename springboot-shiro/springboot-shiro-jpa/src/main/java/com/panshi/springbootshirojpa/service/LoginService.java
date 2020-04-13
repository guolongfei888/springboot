package com.panshi.springbootshirojpa.service;

import com.panshi.springbootshirojpa.model.Role;
import com.panshi.springbootshirojpa.model.User;

/**
 * @ClassName LoginService
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  11:58
 * @Version
 */
public interface LoginService {
    User addUser(User user);

    Role addRole(Role role);

    User findByName(String name);
}
