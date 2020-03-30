package com.panshi.springbootweb.controller;

import com.panshi.springbootweb.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyExceptionHandler
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  10:41
 * @Version
 */
@ControllerAdvice
public class MyExceptionHandler {

    // 1.浏览器 客户端返回的都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handlerException(Exception e) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message",e.getMessage());
//        return map;
//    }


    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",400);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());

        request.setAttribute("ext",map);

        // 转发到error
        return "forward:/error";
    }
}
