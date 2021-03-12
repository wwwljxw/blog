package com.ljx.blog.Mapper;

import com.ljx.blog.pojo.TTag;


import java.util.List;

public interface TTagMapper {

    List<TTag> selectByList();

//    List<TTag> getBlogTag();

    TTag selectById(long id);

    TTag selectByNameAfter(String name);

    int insert(String name);

    int updateById(String name,long id);

    int deleteById(long id);
}
