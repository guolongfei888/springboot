package com.panshi.springbootfilter.config;

import com.panshi.springbootfilter.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FilterConfig
 * @Description
 * @Author guolongfei
 * @Date 2020/5/12  15:24
 * @Version
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/1/*");
        registration.setName("FilterConfig");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean registrationBean2 () {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/2/*");
        registration.setName("FilterConfig1");
        registration.setOrder(2);
        return registration;
    }
}
