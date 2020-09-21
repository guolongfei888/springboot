package com.panshi.security06.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***failureUrl() 指定认证失败后Url，defaultSuccessUrl() 指定认证成功后Url。我们可以通过设置 successHandler() 和 failureHandler() 来实现自定义认证成功、失败处理。

 PS：当我们设置了这两个后，需要去除 failureUrl() 和 defaultSuccessUrl() 的设置，否则无法生效。这两套配置同时只能存在一套。

 * 自定义 CustomAuthenticationSuccessHandler 类来实现 AuthenticationSuccessHandler 接口，用来处理认证成功后逻辑：
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功,{}", authentication);

        response.sendRedirect("/");
    }
}
