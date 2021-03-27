package com.ljx.blog.dao;

import com.ljx.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**

 * @Description: 文章分类持久层接口
 * @Date: Created in 15:14 2020/3/27

 */
@Mapper
@Repository
public interface TypeDao {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAllTypeAndBlog();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);


}