package com.panshi.mybatisplus01.service;

import com.panshi.mybatisplus01.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuan
 * @since 2020-09-24
 */
public interface UserService extends IService<User> {

    List<User> selectAll();
}
