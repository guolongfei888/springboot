package com.panshi.springbootcache.service;

import com.panshi.springbootcache.bean.Employee;
import com.panshi.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @ClassName EmployeeService
 * @Description
 * @Author guolongfei
 * @Date 2020/3/31  14:02
 * @Version
 */
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存: 相同的数据,直接从缓存中获取,不用调用方法
     *
     * CacheManager 管理多个Cache组件的,对缓存的真正CRUD在Cache组件中,每一个缓存组件有自己唯一一个名字:
     * 几个属性:
     *      cacheNames/value: 指定缓存组件的名字;将方法的返回结果放在哪个缓存中,是数组的方式,可以指定多个缓存;
     *      key: 缓存数据使用的key: 可以用它来指定.默认是使用方法参数的值, 1- 方法的返回值
     *          编写Spel: #id;参数id的值  #a0  #p0  #root.args[0]
     *          getEmp[1]
     *
     *      keyGenerator: key的生成器: 可以自己制定key的生成器的组件id
     *              key/keyGenerator: 二选一使用
     *      cacheManager: 指定缓存管理器 或者cacheResolver指定获取解析器
     *      condition: 指定符合条件的情况下缓存
     *      unless: 否定缓存; 当unless指定的条件为true,方法的返回值就不会被缓存
     *          unless="#result == null"
     *      sync: 是否使用异步模式 ,异步模式不支持unless
     *
     * @param id
     * @return
     */
    @Cacheable(
            cacheNames = {"emp"}
          /*  key = "#root.methodName+'['+#id+']'"
            keyGenerator = "myKeyGenerator"
            condition = "#id>1",unless = "#a0==2"*/
    )
    public Employee getEmpById(Integer id) {
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut: 既调用方法.有更新缓存数据
     * 运行时机:
     *  1. 先调用方法
     *  2. 将目标方法的结果缓存起来
     *测试步骤
     * 1. 查询1号员工
     *   key: 1  value:  lastName: 张三
     * 2. 以后查询还是之前的结果
     * 3. 更新1号员工:
     *      将方法的返回值也放进缓存了;
     *      key: 传入的employee对象   值: 返回的employee对象
     * 4. 查询1号员工?
     *      应该是更新后的员工:
     *          key="#employee.id" : 使用传入的参数的员工id
     *          key="#result.id" : 使用放回后的id
     *          @Cacheable的key是不能用#result
     *      为什么是没更新前的? [1号与昂没有在缓存中更新]
     * @param employee
     * @return
     */
    @CachePut(/*value = "emp",*/key = "#employee.id")
    public Employee update(Employee employee) {
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict: 缓存清除
     * key: 指定要清除的数据
     * allEntries=true, 清除缓存中所有数据
     * beforeInvocation = false; 缓存的清除是否在方法之前执行
     *      默认代表缓存清除操作是在方法执行之后执行;如果出现异常缓存就不会清除
     *beforeInvocation = true;
     *      默认代表缓存清除操作是在方法执行之前执行;无论是否出现异常,缓存都清除
     * @param id
     */
    @CacheEvict(/*value = "emp",*//*key = "#id",*/allEntries = true,beforeInvocation = true)
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp:"+id);
    }


    // 复杂缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp",*/key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp",*/key = "#result.id"),
                    @CachePut(/*value = "emp",*/key = "#result.email")
            }
    )
    public Employee getEmpByName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }

}
