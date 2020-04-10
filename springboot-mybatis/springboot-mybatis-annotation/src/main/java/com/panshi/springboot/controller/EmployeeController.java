package com.panshi.springboot.controller;

import com.panshi.springboot.bean.Employee;
import com.panshi.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EmployeeController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  19:30
 * @Version
 */
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/emp")
    public int insertEmp(Employee employee) {
        int count = employeeMapper.insertEmp(employee);
        if (count<1) {
            return -1;
        }
        return count;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id) {
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }
}
