package com.panshi.listener.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/***
 * @Auther: guo
 * @Date: 15:13 2020/9/25
 */
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("HelloApplicationContextInitializer...initialize  "+configurableApplicationContext);
    }
}
