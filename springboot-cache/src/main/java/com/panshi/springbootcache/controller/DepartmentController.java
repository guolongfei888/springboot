package com.panshi.springbootcache.controller;

import com.panshi.springbootcache.bean.Department;
import com.panshi.springbootcache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DepartmentController
 * @Description
 * @Author guolongfei
 * @Date 2020/4/8  20:42
 * @Version
 */
@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id) {
        Department dept = departmentService.getDeptById(id);
        return dept;
    }
}
