package com.panshi.security02.service;

import com.panshi.security02.entity.SysRole;
import com.panshi.security02.entity.SysUser;
import com.panshi.security02.entity.SysUserRole;
import com.panshi.security02.mapper.SysRoleMapper;
import com.panshi.security02.mapper.SysUserMapper;
import com.panshi.security02.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @Auther: guo
 * @Date: 10:55 2020/9/21
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;


    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }

    public void insertUser(SysUser user) {

//        if (user!=null ){
//            SysUser sysUser = userMapper.selectByName(user.getName());
//            if (sysUser!=null) {
//                System.out.println("用户名已存在");
//                return;
//            }
            userMapper.insertUser(user);
            SysUser sysUser = userMapper.selectByName(user.getName());
            // 默认新增用户的权限为ROLE_USER,该权限在用户表中已存在,不需要在添加
//            SysRole role = new SysRole();
//            role.setName("ROLE_USER");
//            roleMapper.insertRole(role);
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getId());
            userRole.setRoleId(2);
            userRoleMapper.insertUserRole(userRole.getUserId(),userRole.getRoleId());
//        }
    }

}
