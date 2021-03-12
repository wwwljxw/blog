package com.ljx.blog.Mapper;

import com.ljx.blog.pojo.TBlog;
import com.ljx.blog.pojo.TBlogTags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBlogMapper {

    TBlog getBlog(Long id);  //后台展示博客
    TBlog getDetailedBlog(@Param("id") Long id);  //博客详情

    List<TBlog> getAllBlog();
    List<TBlog> getByTypeId(Long typeId);  //根据类型id获取博客
    List<TBlog> getByTagId(Long tagId);  //根据标签id获取博客
    List<TBlog> getIndexBlog();  //主页博客展示
    List<TBlog> getAllRecommendBlog();  //推荐博客展示
    List<TBlog> getSearchBlog(String query);  //全局搜索博客
    List<TBlog> searchAllBlog(Blog blog);  //后台根据标题、分类、推荐搜索博客
    List<String> findGroupYear();  //查询所有年份，返回一个集合

    List<TBlog> findByYear(@Param("year") String year);  //按年份查询博客

    int saveBlog(TBlog blog);

    int saveBlogAndTag(TBlogTags blogAndTag);

    int updateBlog(TBlog blog);

    int deleteBlog(Long id);
}
