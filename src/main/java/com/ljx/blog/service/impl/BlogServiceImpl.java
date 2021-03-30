package com.ljx.blog.service.impl;

import com.ljx.blog.dao.BlogDao;
import com.ljx.blog.entity.Blog;
import com.ljx.blog.queryvo.*;
import com.ljx.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 博客列表业务层接口实现类
 * @author Lin
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public List<BlogQuery> getAllBlogQuery() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        return blogDao.updateBlog(showBlog);
    }

    @Override
    public int saveBlog(Blog blog) {

        return blogDao.saveBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }


    /**
     * @Description 查询首页最新博客列表信息
     */
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }

    /**
     *  @Description 查询首页最新推荐信息
     */
    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        return blogDao.getAllRecommendBlog();
    }

    /**
     * @Description 搜索博客列表
     * @param query 搜索关键词
     */
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    /**
     * @Description 统计博客总数
     */
    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    /**
     * @Description 统计访问总数
     */
    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    /**
     * @Description 统计评论总数
     */
    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    /**
     * @Description 统计留言总数
     */
    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }


}
