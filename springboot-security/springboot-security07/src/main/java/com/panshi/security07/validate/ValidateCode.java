package com.panshi.security07.validate;

import java.time.LocalDateTime;

/***
 * 创建手机验证码实体
 * 第一步先创建一个手机验证码实体类，包含验一个证码字段和一个过期时间字段，
 * 还提供了两种构造器，提供两种不同设置过期时间的方式
 */
public class ValidateCode {
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code,int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
