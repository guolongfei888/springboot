package com.panshi.springbootshiro01.mapper;

import com.panshi.springbootshiro01.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName UserMapper
 * @Description
 * @Author guolongfei
 * @Date 2020/3/26  17:14
 * @Version
 */
@Mapper
public interface UserMapper {
    @Insert("insert into sys_user(id,userName,passWord) values(null,#{username},#{password})")
    int insert(User user);

    @Select("select id,username,password from sys_user where username=#{username}")
    User selectByUsername(String username);
}
