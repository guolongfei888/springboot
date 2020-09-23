package com.panshi.mybatisplus01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.panshi.mybatisplus01.mapper")
@SpringBootApplication
public class SpringbootMybatisplus01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisplus01Application.class, args);
    }

}
