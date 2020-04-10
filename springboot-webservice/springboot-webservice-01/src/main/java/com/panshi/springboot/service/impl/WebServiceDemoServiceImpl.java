package com.panshi.springboot.service.impl;

import com.panshi.springboot.service.WebServiceDemoService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @ClassName WebServiceDemoServiceImpl
 * @Description
 * @Author guolongfei
 * @Date 2020/4/10  14:11
 * @Version
 */
@WebService(serviceName = "fileDeviceService",//对外发布的服务名
        targetNamespace = "http://service.springboot.panshi.com",//指定你想要的名称空间，通常使用使用包名反转
        endpointInterface = "com.panshi.springboot.service.WebServiceDemoService")
@Service
public class WebServiceDemoServiceImpl implements WebServiceDemoService {
    @Override
    public String hello(String name) {
        return "hello"+name;
    }
}
