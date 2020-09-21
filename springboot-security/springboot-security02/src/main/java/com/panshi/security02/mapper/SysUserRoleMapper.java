package com.panshi.security02.mapper;

import com.panshi.security02.entity.SysUserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/***
 * @Auther: guo
 * @Date: 10:54 2020/9/21
 */
@Mapper
public interface SysUserRoleMapper {
//    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);

    void insertUserRole(@Param("userId")Integer userId,@Param("roleId")Integer roleId);
}
