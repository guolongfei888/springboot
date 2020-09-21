package com.panshi.security05;

import com.panshi.security05.servlet.VerifyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootSecurity05Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurity05Application.class, args);
    }

    @Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new VerifyServlet());
        registrationBean.addUrlMappings("/getVerifyCode");
        return registrationBean;
    }
}
