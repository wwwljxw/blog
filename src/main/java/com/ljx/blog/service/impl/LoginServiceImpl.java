package com.ljx.blog.service.impl;

import com.ljx.blog.Mapper.TUserMapper;
import com.ljx.blog.pojo.TUser;
import com.ljx.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public TUser getNaPwd(String username) {
        return tUserMapper.getNaPwd(username);
    }
}
