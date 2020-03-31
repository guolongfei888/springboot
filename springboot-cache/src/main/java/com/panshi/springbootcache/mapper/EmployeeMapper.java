package com.panshi.springbootcache.mapper;

import com.panshi.springbootcache.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName EmployeeMapper
 * @Description
 * @Author guolongfei
 * @Date 2020/3/31  13:59
 * @Version
 */
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE id = #{id}")
    Employee getEmpById(Integer id);

    @Insert("INSERT INTO employee(lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    void insertEmp(Employee employee);

    @Update("UPDATE employee SET lastName = #{lastName},email = #{email},gender = #{gender},d_id = #{dId} WHERE id = #{id}")
    void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    void deleteEmpById(Integer id);

    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    Employee getEmpByLastName(String lastName);
}
