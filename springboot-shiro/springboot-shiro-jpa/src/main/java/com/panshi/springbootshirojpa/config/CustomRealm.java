package com.panshi.springbootshirojpa.config;

import com.panshi.springbootshirojpa.model.Permission;
import com.panshi.springbootshirojpa.model.Role;
import com.panshi.springbootshirojpa.model.User;
import com.panshi.springbootshirojpa.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName CustomRealm
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  14:03
 * @Version
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        // 查询用户名称
        User user = loginService.findByName(name);
        // 添加角色和权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            // 添加角色
            info.addRole(role.getRoleName());
            for (Permission permission:role.getPermissions()) {
                // 添加权限
                info.addStringPermission(permission.getPermission());
            }
        }

        return info;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        // 获取用户信息
        String name = (String) authenticationToken.getPrincipal();
        String pass = new String((char[]) authenticationToken.getCredentials()) ;
        User user = loginService.findByName(name);
        if (user==null) {
            // 这里返回后悔报出对应的异常
            return null;
        } else {
            if (name == null) {
                return null;
            }
            if (!pass.equals(user.getPassword())) {
                return null;
            }
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name,user.getPassword().toString(),getName());
            return info;
        }

    }
}
