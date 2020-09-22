package com.panshi.security02.mapper;

import com.panshi.security02.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/***
 * @Auther: guo
 * @Date: 10:52 2020/9/21
 */
@Mapper
public interface SysUserMapper {
//    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUser selectById(Integer id);

//    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUser selectByName(String name);

//    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")// 主键自增返回
//    @Insert("insert into sys_user values (null,#{name},#{password})")
    void insertUser(SysUser user);
}