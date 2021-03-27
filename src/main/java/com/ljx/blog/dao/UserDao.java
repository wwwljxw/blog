package com.ljx.blog.dao;


import com.ljx.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户持久层接口

 * @Date: Created in 11:11 2020/3/26

 */
@Mapper
@Repository
public interface UserDao {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}