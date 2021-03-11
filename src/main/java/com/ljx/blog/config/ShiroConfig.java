package com.ljx.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean
     * @param defaultWebSecurityManager 安全管理器
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//        添加shiro的内置过滤器
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/admin/login","anon");
        map.put("/admin/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//        设置登录接口
        shiroFilterFactoryBean.setLoginUrl("/admin");

        return shiroFilterFactoryBean;
    }

    /**
     * DefaultWebSecurityManager安全管理器
     */
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        关联realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 自定义Realm
     */
    @Bean("getRealm")
    public Realm getRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }
}
