package com.panshi.springbootweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


 //使用WebMvcConfigurer可以来扩展SpringMVC的功能
@Configuration
//@EnableWebMvc
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /superbeyone 请求来到 success
        registry.addViewController("/superbeyone").setViewName("success");
    }

     //所有的WebMvcConfigurerAdapter组件都会一起起作用
//     @Bean
//     public WebMvcConfigurer webMvcConfigurer() {
//        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("login");
//                registry.addViewController("/index.html").setViewName("login");
//            }
//        };
//        return webMvcConfigurer;
//    }
}
