package com.panshi.springbootcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 搭建基本环境:
 * 1. 创建数据表
 * 2. 创建javaBean封装数据
 * 3. 整合mybatis操作数据库
 *      配置数据源信息
 *      使用注解版mybatis
 *      @MapperScan 指定需要扫描的mapper接口
 *
 *二. 快速体验缓存
 *    步骤:
 *      开启基于注解的缓存 @EnableCaching
 *      标注缓存注解即可
 *        @Cacheable
 */
@MapperScan("com.panshi.springbootcache.mapper")
@EnableCaching
@SpringBootApplication
public class SpringbootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheApplication.class, args);
    }

}
