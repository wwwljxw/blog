package com.ljx.blog.Mapper;

import com.ljx.blog.pojo.TType;

import java.util.List;


public interface TTypeMapper {


    List<TType> selectByList();

    TType selectById(long id);

    TType selectByNameAfter(String name);

    int insert(String name);

    int updateById(String name,long id);

    int deleteById(long id);
}
