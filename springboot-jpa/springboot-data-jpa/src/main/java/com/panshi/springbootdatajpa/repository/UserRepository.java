package com.panshi.springbootdatajpa.repository;

import com.panshi.springbootdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserRepository
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  20:13
 * @Version
 */
// 继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {

}
