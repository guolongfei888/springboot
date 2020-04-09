package com.panshi.springbootcache;

import com.panshi.springbootcache.bean.Employee;
import com.panshi.springbootcache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootCacheApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    void contextLoads() {
//        stringRedisTemplate.opsForValue().append("msg","hengheng");

//        stringRedisTemplate.opsForList().leftPush("mylist","1");
//        stringRedisTemplate.opsForList().leftPush("mylist","2");

        Employee emp = employeeMapper.getEmpById(1);
        // 默认如果保存对象,使用jdk序列化机制,序列化后的数据保存到redis中
//        redisTemplate.opsForValue().set("emp-01",emp);
        // 1. 蒋书记以json的方式保存
        // (1) 自己讲对象转为json
        // (2) redisTemplate 默认的序列号规则
        empRedisTemplate.opsForValue().set("emp-02",emp);
    }

}
