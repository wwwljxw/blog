package com.ljx.blog.Mapper;

import com.ljx.blog.pojo.TUser;
import org.apache.catalina.User;

public interface TUserMapper {

    /**
     *
     * @param username 用户名
     * @return  验证用户名密码
     */
   TUser getNaPwd(String username);
}
