package com.ljx.blog.service;

import com.ljx.blog.queryvo.BlogQuery;
import com.ljx.blog.queryvo.ShowBlog;

import java.util.List;

/**
 * @Description 博客列表业务层接口
 * @author Lin
 */
public interface BlogService {

    List<BlogQuery> getAllBlogQuery();

    ShowBlog getBlogById(Long id);

    int updateBlog(ShowBlog showBlog);


}
