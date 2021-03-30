package com.ljx.blog;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.dao.TypeDao;
import com.ljx.blog.dao.UserDao;
import com.ljx.blog.entity.Type;
import com.ljx.blog.entity.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        User user = userDao.findByUsernameAndPassword("linjx");
        //        将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
//        密码以MD5的方式加密2次
        SimpleHash md5 = new SimpleHash("MD5", "1111", salt, 2);
        System.out.println("加密后的密码是：" + md5.toString());
    }

}
