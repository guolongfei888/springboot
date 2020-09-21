package com.panshi.security02.entity;

import java.io.Serializable;

/***
 * @Auther: guo
 * @Date: 10:50 2020/9/21
 */
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1241480822060423883L;
    private Integer userId;

    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
