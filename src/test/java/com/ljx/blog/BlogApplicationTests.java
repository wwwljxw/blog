package com.ljx.blog;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.dao.BlogDao;
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
    BlogDao blogDao;

    @Test
    void contextLoads() {
        blogDao.getBlogTotal();
    }

}
