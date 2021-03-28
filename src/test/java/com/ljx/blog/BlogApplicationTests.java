package com.ljx.blog;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.dao.TypeDao;
import com.ljx.blog.entity.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    TypeDao typeDao;

    @Test
    void contextLoads() {
        PageHelper.startPage(1, 5);
        List<Type> allType = typeDao.getAllType();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        pageInfo.getList().forEach(System.out::println);
    }

}
