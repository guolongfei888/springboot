package com.panshi.springbootweb.config;

import com.panshi.springbootweb.component.MyLocaleResolver;
import com.panshi.springbootweb.interceptor.LoginHandlerInterceptor;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


 //使用WebMvcConfigurer可以来扩展SpringMVC的功能
@Configuration
//@EnableWebMvc
public class MyConfig implements WebMvcConfigurer {

    // WebServerFactoryCustomizer
//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//
//            // 定制嵌入式的Servlet容器相关的规则
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                factory.setPort(8082);
//            }
//        };
//    }

     @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /superbeyone 请求来到 success
        registry.addViewController("/superbeyone").setViewName("success");
    }

     //所有的WebMvcConfigurerAdapter组件都会一起起作用
     @Bean //将组件注册在容器
     public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}
