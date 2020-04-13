package com.panshi.springbootshirojpa.service.impl;

import com.panshi.springbootshirojpa.dao.RoleDao;
import com.panshi.springbootshirojpa.dao.UserDao;
import com.panshi.springbootshirojpa.model.Permission;
import com.panshi.springbootshirojpa.model.Role;
import com.panshi.springbootshirojpa.model.User;
import com.panshi.springbootshirojpa.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LoginServiceImpl
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  11:58
 * @Version
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    // 添加用户
    @Override
    public User addUser(User user) {
        return userDao.save(user);
    }

    // 添加角色
    @Override
    public Role addRole(Role role) {
        User user = userDao.findByName(role.getUser().getName());
        role.setUser(user);
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleDao.save(role);
        return role;
    }
    //查询用户通过用户名
    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
