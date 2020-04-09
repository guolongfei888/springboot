package com.panshi.springbootshiro01.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName MD5Utils
 * @Description
 * @Author guolongfei
 * @Date 2020/4/9  14:59
 * @Version
 */
public class MD5Utils {
    // shiro提供了SimpleHash类帮助我们快速加密：
    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "salt"), 2).toHex();
        return md5Pwd;
    }
}
