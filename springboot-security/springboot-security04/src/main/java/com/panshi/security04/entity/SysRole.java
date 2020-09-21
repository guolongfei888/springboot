package com.panshi.security04.entity;

import java.io.Serializable;

/***
 * @Auther: guo
 * @Date: 10:51 2020/9/21
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = -4278957055453661749L;
    private Integer id;

    private String name;

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
}
