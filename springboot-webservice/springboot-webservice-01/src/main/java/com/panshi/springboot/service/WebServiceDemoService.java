package com.panshi.springboot.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @WebService：标明是个webservice服务，发布的时候会带上这个类。
 * @WeBMethod：webservice中发布的方法
 * @WebParam：对参数的别名，不写也不影响，但是参数在wsdl中看起来是arg0，不利于理解。
 */
@WebService
public interface WebServiceDemoService {
    @WebMethod
    public String hello(@WebParam(name = "name") String name);
}
