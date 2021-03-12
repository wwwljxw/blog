package com.ljx.blog;

import com.github.pagehelper.PageInfo;
import com.ljx.blog.Mapper.TTypeMapper;
import com.ljx.blog.Mapper.TUserMapper;
import com.ljx.blog.pojo.TType;
import com.ljx.blog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private TypeService typeService;

    @Test
    void contextLoads() {
//        分页查询
        PageInfo<TType> allByPage = typeService.findAllByPage(1, 3);
        List<TType> list = allByPage.getList();
        list.forEach(System.out::println);

        int test = typeService.insert("测试");
        System.out.println("添加是否成功：" + test);
    }

}
