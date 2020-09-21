package com.panshi.security02.mapper;

import com.panshi.security02.entity.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/***
 * @Auther: guo
 * @Date: 10:53 2020/9/21
 */
@Mapper
public interface SysRoleMapper {
//    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(Integer id);

    SysRole selectByName(String name);

//    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
//    @Insert("insert into sys_role values(null,#(name))")
    void insertRole(SysRole role);
}
