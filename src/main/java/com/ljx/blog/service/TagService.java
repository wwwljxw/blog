package com.ljx.blog.service;

import com.github.pagehelper.PageInfo;
import com.ljx.blog.pojo.TTag;


public interface TagService {
    PageInfo<TTag> findAllByPage(int page, int offset);

    TTag selectById(long id);

    TTag selectByName(String name);

    int insert(String name);

    int updateById(String name,long id);

    int deleteById(long id);
}
