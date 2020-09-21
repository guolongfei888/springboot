package com.panshi.security03.mapper;

import com.panshi.security03.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * @Auther: guo
 * @Date: 10:54 2020/9/21
 */
@Mapper
public interface SysUserRoleMapper {
//    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);

    void insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
