package com.panshi.springboot.controller;

import com.panshi.springboot.bean.Department;
import com.panshi.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DeptController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  17:36
 * @Version
 */
@RestController
public class DeptController {
    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/dept")
    public Department insertDept(Department department) {
        int count = departmentMapper.insertDept(department);
        if (count < 1) {
            return null;
        }
        return department;
    }


    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        Department dept = departmentMapper.getDeptById(id);
        return dept;
    }

}
