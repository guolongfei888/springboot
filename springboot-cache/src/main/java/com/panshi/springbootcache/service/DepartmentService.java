package com.panshi.springbootcache.service;

import com.panshi.springbootcache.bean.Department;
import com.panshi.springbootcache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @ClassName DepartmentService
 * @Description
 * @Author guolongfei
 * @Date 2020/4/8  20:40
 * @Version
 */
@Service
@Cacheable(cacheNames = "dept",cacheManager = "departmentRedisCacheManager")
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 缓存的数据能存入Redis
     * 第二次从缓存中查询就不能反序列化回来
     * 存的是dept的json数据,CacheManager默认使用RedisTemplate<Object,Employee>操作redis
     *
     * @param id
     * @return
     */
//    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id) {
        Department dept = departmentMapper.getDeptById(id);
        return dept;
    }
}
