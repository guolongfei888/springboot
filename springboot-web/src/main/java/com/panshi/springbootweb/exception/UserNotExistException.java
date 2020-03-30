package com.panshi.springbootweb.exception;

/**
 * @ClassName UserNotExistException
 * @Description
 * @Author guolongfei
 * @Date 2020/3/30  10:36
 * @Version
 */
public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("用户不存在");
    }
}
