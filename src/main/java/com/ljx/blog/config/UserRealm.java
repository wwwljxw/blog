package com.ljx.blog.config;

import com.ljx.blog.pojo.TUser;
import com.ljx.blog.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    //    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *
     * @param authenticationToken 封装好的用户名和密码
     * @return  验证结果
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        TUser user = loginService.getNaPwd(usernamePasswordToken.getUsername());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

//        数据库查询不到的话
        if (user == null){
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "");
//        将用户信息去除密码后传至前台
        user.setPassword(null);
        session.setAttribute("user",user);
        return simpleAuthenticationInfo;
    }
}