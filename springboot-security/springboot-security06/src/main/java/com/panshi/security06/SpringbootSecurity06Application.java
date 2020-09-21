package com.panshi.security06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
public class SpringbootSecurity06Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurity06Application.class, args);
    }

}
