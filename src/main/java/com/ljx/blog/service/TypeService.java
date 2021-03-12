package com.ljx.blog.service;

import com.github.pagehelper.PageInfo;
import com.ljx.blog.pojo.TType;

public interface TypeService {

    PageInfo<TType> findAllByPage(int page, int offset);

    TType selectById(long id);

    TType selectByName(String name);

    int insert(String name);

    int updateById(String name,long id);

    int deleteById(long id);
}
