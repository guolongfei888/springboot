package com.panshi.springbootcache.mapper;

import com.panshi.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName DepartmentMapper
 * @Description
 * @Author guolongfei
 * @Date 2020/3/31  14:00
 * @Version
 */
public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    Department getDeptById(Integer id);
}
