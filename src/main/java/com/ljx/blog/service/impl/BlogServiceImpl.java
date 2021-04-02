package com.ljx.blog.service.impl;

import com.ljx.blog.dao.BlogDao;
import com.ljx.blog.entity.Blog;
import com.ljx.blog.queryvo.*;
import com.ljx.blog.service.BlogService;
import com.ljx.blog.utils.MarkdownUtils;
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

    /**
     * @Description 查询博客详情
     * @param id    要查询的博客的id
     */
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
//        查询博客详情
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
//        将markdown格式文档转换为html
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
//        自增浏览量
        blogDao.updateViews(id);
//        查询评论量并更新
        blogDao.getCommentCountById(id);
        return detailedBlog;
    }

    /**
     * @Description 根据TypeId查询博客列表，显示在前台分类页面
     */
    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }


}
