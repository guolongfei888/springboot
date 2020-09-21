package com.panshi.security05.mapper;

import com.panshi.security05.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/***
 * @Auther: guo
 * @Date: 23:07 2020/9/21
 */
@Mapper
public interface SysPermissionMapper {
    List<SysPermission> listByRoleId(Integer roleId);
}
