package com.panshi.springbootcache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panshi.springbootcache.bean.Department;
import com.panshi.springbootcache.bean.Employee;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.net.UnknownHostException;
import java.time.Duration;

/**
 * @ClassName MyRedisConfig
 * @Description
 * @Author guolongfei
 * @Date 2020/4/1  10:59
 * @Version
 */
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> ser = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Department> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> ser = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisCacheManager employeeRedisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(1))   // 设置缓存过期时间为一天
                        .disableCachingNullValues()     // 禁用缓存空值，不缓存null校验
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new
//                                GenericJackson2JsonRedisSerializer()
                                Jackson2JsonRedisSerializer<Employee>(Employee.class)));     // 设置CacheManager的值序列化方式为json序列化，可加入@Class属性
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();     // 设置默认的cache组件
    }

    @Primary
    @Bean
    public RedisCacheManager departmentRedisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(1))   // 设置缓存过期时间为一天
                        .disableCachingNullValues()     // 禁用缓存空值，不缓存null校验
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new
//                                GenericJackson2JsonRedisSerializer()
                                Jackson2JsonRedisSerializer<Department>(Department.class)));     // 设置CacheManager的值序列化方式为json序列化，可加入@Class属性
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();     // 设置默认的cache组件
    }


}
