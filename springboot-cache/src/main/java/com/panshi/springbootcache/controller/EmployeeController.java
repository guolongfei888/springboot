package com.panshi.springbootcache.controller;

import com.panshi.springbootcache.bean.Employee;
import com.panshi.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EmployeeController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/31  14:03
 * @Version
 */
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeService.getEmpById(id);
    }

    @RequestMapping("/emp")
    public Employee update(Employee employee) {
        employeeService.update(employee);
        return employee;
    }

    @RequestMapping("/delemp")
    public String deleteEmp(Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }

    @RequestMapping("/emp/lastname/{lastName}")
    public Employee getEmpByName(@PathVariable("lastName") String lastName) {
        Employee emp = employeeService.getEmpByName(lastName);
        return emp;
    }
}
