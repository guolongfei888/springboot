package com.panshi.springbootshirojpa.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName MyException
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  14:51
 * @Version
 */
@ControllerAdvice

public class MyException {
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String errorHandler(Exception e) {
        return "权限认证未通过";
    }
}
