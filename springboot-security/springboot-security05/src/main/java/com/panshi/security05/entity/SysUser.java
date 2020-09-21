package com.panshi.security05.entity;

import java.io.Serializable;

/***
 * @Auther: guo
 * @Date: 10:42 2020/9/21
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 975773542582169117L;
    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
