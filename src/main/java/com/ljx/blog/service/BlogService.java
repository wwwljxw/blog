package com.ljx.blog.service;

import com.ljx.blog.entity.Blog;
import com.ljx.blog.queryvo.BlogQuery;
import com.ljx.blog.queryvo.SearchBlog;
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

    int saveBlog(Blog blog);

    void deleteBlog(Long id);

    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

}
