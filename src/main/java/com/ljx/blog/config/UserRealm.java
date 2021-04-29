package com.ljx.blog.config;

import com.ljx.blog.entity.User;
import com.ljx.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Lin
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * @param authenticationToken 封装好的用户名和密码
     * @return 验证结果
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        获取用户输入的账号密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = userService.checkUser(usernamePasswordToken.getUsername());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

//        数据库查询不到的话
        if (user == null) {
            return null;
        }

//        将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
//        密码以MD5的方式加密2次
        SimpleHash md5 = new SimpleHash("MD5", usernamePasswordToken.getPassword(), salt, 2);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),usernamePasswordToken.getPassword(), "");
//        将用户信息去除密码后传至前台
        user.setPassword(null);
        session.setAttribute("user", user);
        return simpleAuthenticationInfo;
    }
}