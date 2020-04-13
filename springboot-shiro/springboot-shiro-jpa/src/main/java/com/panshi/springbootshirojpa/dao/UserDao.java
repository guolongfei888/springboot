package com.panshi.springbootshirojpa.dao;

import com.panshi.springbootshirojpa.model.User;

/**
 * @ClassName UserDao
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  11:56
 * @Version
 */
public interface UserDao extends BaseDao<User,Long> {
    User findByName(String name);
}
