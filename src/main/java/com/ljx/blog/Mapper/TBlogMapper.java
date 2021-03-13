package com.ljx.blog.Mapper;

import com.ljx.blog.pojo.TBlog;
import com.ljx.blog.pojo.TBlogTags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBlogMapper {

    TBlog getBlog(Long id);  //后台展示博客

    List<TBlog> getAllBlog();

    List<TBlog> searchAllBlog(TBlog blog);  //后台根据标题、分类、推荐搜索博客

    int saveBlog(TBlog blog);

    int updateBlog(TBlog blog);

    int deleteBlog(Long id);

}
