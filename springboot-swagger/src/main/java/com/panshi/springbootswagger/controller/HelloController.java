package com.panshi.springbootswagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description
 * @Author guolongfei
 * @Date 2020/3/25  19:06
 * @Version
 */
// 类注解
@Api(value = "desc of class")
@RestController
public class HelloController {

    @ApiOperation(value = "desc of method",notes = "")
    @GetMapping(value = "/hello")
    public Object hello(@ApiParam(value = "desc of param",required = true) @RequestParam String name) {
        return "Hello "+name+"!";
    }
}
