package com.panshi.springbootweb.interceptor;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @ClassName MyErrorAttributes
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  10:55
 * @Version
 */
//给容器中加入我们自己定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    // 返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorMap = super.getErrorAttributes(webRequest, includeStackTrace);
        // 自定义
        errorMap.put("company","superbeyone");
        // 我们的异常处理器携带的数据
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext",0);
        errorMap.put("ext",ext);
        return errorMap;
    }
}
