package com.panshi.security07.execption;


import org.springframework.security.core.AuthenticationException;
/***
 * @Auther: guo
 * @Date: 21:23 2020/9/22
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -5969069372235869727L;

    public ValidateCodeException (String msg) {
        super(msg);
    }
}
