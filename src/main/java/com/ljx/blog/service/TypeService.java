package com.ljx.blog.service;


import com.ljx.blog.entity.Type;

import java.util.List;

/**
 * @Description 分类业务层接口
 * @author Lin
 */
public interface TypeService {
    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);
}
