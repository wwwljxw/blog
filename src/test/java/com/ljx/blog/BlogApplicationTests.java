package com.ljx.blog;

import com.github.pagehelper.PageInfo;
import com.ljx.blog.Mapper.TBlogMapper;
import com.ljx.blog.pojo.TBlog;
import com.ljx.blog.pojo.TType;
import com.ljx.blog.service.BlogsService;
import com.ljx.blog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private TBlogMapper tBlogMapper;

    @Test
    void contextLoads() {
        List<TBlog> allBlog = tBlogMapper.getAllBlog();
        for (int i = 0; i < allBlog.size(); i++) {
            TBlog tBlog = allBlog.get(i);
            System.out.println(tBlog.getType());
        }
    }

}
