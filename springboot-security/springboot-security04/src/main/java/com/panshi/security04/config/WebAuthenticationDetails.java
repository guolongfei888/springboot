package com.panshi.security04.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/***
 * 获取用户登录时携带的额外信息
 *
 * Spring security 默认只会处理用户名和密码信息,
 * 然而WebAuthenticationDetails: 该类提供了获取用户登录时携带的额外信息的功能，默认提供了 remoteAddress 与 sessionId 信息。
 */
class CustomWebAuthenticationDetails  extends WebAuthenticationDetails  {
    private static final long serialVersionUID = -5094676573448160627L;
    private final String verifyCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        // verifyCode为页面中验证码的name
        verifyCode = request.getParameter("verifyCode");
    }


    public String getVerifyCode() {
        return verifyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; VerifyCode: ").append(this.getVerifyCode());
        return sb.toString();
    }
}
