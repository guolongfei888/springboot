package com.panshi.springbootdatajpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName User
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  20:06
 * @Version
 */
// 使用JPA注解配置映射关系
@Entity  // 告诉JPA这是一个实体类(和数据表映射的类)
// @Table: 对应数据库中的表, 必须, name=表名, Indexes是声明表里的索引, columnList是索引的列, 同时声明此索引列是否唯一, 默认false
@Table(name = "sys_user", indexes = {@Index(name = "id", columnList = "id", unique = true),
        @Index(name = "username", columnList = "user_name", unique = true)})
public class User implements Serializable {

    private static final long serialVersionUID = 1960404266651693824L;
    @Id  // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键自增
    private Integer id;
    @Column
    private Integer age;
    @Column(name = "user_name",length = 50) // 这是和数据表对应的一个列
    private String username;
    @Column // 默认列名就是属性名
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
