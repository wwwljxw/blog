package com.ljx.blog.service.impl;

import com.ljx.blog.dao.UserDao;
import com.ljx.blog.entity.User;
import com.ljx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 用户业务层接口实现类
 * @author Lin
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * @param username  用户名
     * @return  返回用户对象
     */
    @Override
    public User checkUser(String username) {
        return userDao.findByUsernameAndPassword(username);
    }
}
