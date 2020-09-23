package com.panshi.mybatisplus01.mapper;

import com.panshi.mybatisplus01.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2020-09-24
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户信息
     * @return list
     */
    List<User> selectAll();
}
