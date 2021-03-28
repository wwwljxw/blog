package com.ljx.blog.service;

import com.ljx.blog.entity.User;

/**
 * @author Lin
 * @Description 用户业务层接口
 */
public interface UserService {
    User checkUser(String username);
}
