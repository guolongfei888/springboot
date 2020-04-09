package com.panshi.springbootshiro01.config;

import com.panshi.springbootshiro01.entity.User;
import com.panshi.springbootshiro01.mapper.UserMapper;
import com.panshi.springbootshiro01.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的Realm类继承AuthorizingRealm类，并且重载doGetAuthorizationInfo和doGetAuthenticationInfo两个方法。
 * doGetAuthorizationInfo： 权限认证，即登录过后，每个身份不一定，对应的所能看的页面也不一样。
 * doGetAuthenticationInfo：身份认证。即登录通过账号和密码验证登陆人的身份信息。
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> set = new HashSet<>();
        set.add("user:show");
        set.add("user:admin");
        info.setStringPermissions(set);
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        //根据用户名从数据库获取密码
//        String password="123";
        User user = userMapper.selectByUsername(userName);
        if (user == null) {
            throw new AccountException("找不到用户");
        }
        String password = user.getPassword();
        if (userName==null) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(MD5Utils.MD5Pwd(userName,userPwd))) {
            throw new AccountException("密码不正确");
        }
//        return new SimpleAuthenticationInfo(userName,password,getName());
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(userName, password,
                ByteSource.Util.bytes(userName + "salt"), getName());
    }
}
