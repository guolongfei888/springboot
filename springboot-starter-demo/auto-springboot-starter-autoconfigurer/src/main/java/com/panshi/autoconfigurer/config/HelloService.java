package com.panshi.autoconfigurer.config;

/***
 * @Auther: guo
 * @Date: 16:11 2020/9/25
 */
public class HelloService {
    private HelloProperties helloProperties;

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHellSuperbeyone(String name){
        return helloProperties.getPrefix()+"-" +name + helloProperties.getSuffix();
    }
}
