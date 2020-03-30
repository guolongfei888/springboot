package com.panshi.springboot.mapper;

import com.panshi.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @ClassName DepartmentMapper
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  17:28
 * @Version
 */
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    @Results({
        @Result(property = "departmentName",column = "department_name")
    })
    Department getDeptById(Integer id);

    @Delete("delete from department where id = #{id}")
    int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id =#{id}")
    int updateDept(Department department);
}
