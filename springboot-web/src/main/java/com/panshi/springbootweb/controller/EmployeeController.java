package com.panshi.springbootweb.controller;

import com.panshi.springbootweb.dao.DepartmentDao;
import com.panshi.springbootweb.dao.EmployeeDao;
import com.panshi.springbootweb.entity.Department;
import com.panshi.springbootweb.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @ClassName EmployeeController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/27  14:22
 * @Version
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps",employees);

        // thymeleaf 默认就会拼串
        // classpath:/templates/xxx.html
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面,查出所有的部门,在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    // 添加员工
    // SpringMVC自动将请求参数和入参对象的属性进行一一绑定,要求请求参数的名字和JavaBean入参的对象的属性名一致
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
//        System.out.println("保存员工信息:"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        // 来到添加页面,查出所有的部门,在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        // 回到修改页面(add是一个修改添加二合一的页面)
        model.addAttribute("depts",departments);
        return "emp/add";
    }


    //员工删除
//    @DeleteMapping("/emp/{id}")
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.POST)
    public String deleteEmp(@PathVariable("id") Integer id) {

        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
