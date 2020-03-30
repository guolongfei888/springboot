package com.panshi.springboot.mapper;

import com.panshi.springboot.bean.Employee;

/**
 * @ClassName EmployeeMapper
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  19:01
 * @Version
 */
public interface EmployeeMapper {
    Employee getEmpById(Integer id);

    int insertEmp(Employee employee);
}
