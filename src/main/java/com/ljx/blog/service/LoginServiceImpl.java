package com.ljx.blog.service;

import com.ljx.blog.Mapper.TUserMapper;
import com.ljx.blog.pojo.TUser;
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
