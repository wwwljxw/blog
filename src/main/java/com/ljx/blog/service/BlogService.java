package com.ljx.blog.service;

import com.ljx.blog.entity.Blog;
import com.ljx.blog.queryvo.*;

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


    List<FirstPageBlog> getAllFirstPageBlog();

    List<RecommendBlog> getRecommendedBlog();


    List<FirstPageBlog> getSearchBlog(String query);


    Integer getBlogTotal();

    Integer getBlogViewTotal();

    Integer getBlogCommentTotal();

    Integer getBlogMessageTotal();


    DetailedBlog getDetailedBlog(Long id);


    int updateViews(Long id);


    int getCommentCountById(Long id);
}
