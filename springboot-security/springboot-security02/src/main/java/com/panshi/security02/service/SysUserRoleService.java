package com.panshi.security02.service;

import com.panshi.security02.entity.SysUserRole;
import com.panshi.security02.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * @Auther: guo
 * @Date: 10:56 2020/9/21
 */
@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}
