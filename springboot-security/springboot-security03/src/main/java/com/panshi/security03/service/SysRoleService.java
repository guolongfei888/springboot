package com.panshi.security03.service;

import com.panshi.security03.entity.SysRole;
import com.panshi.security03.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @Auther: guo
 * @Date: 10:55 2020/9/21
 */
@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }
}
