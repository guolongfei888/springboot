package com.panshi.springbootasyncdemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootAsyncDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAsyncDemo2Application.class, args);
    }

}
