package com.ljx.blog.service.impl;

import com.ljx.blog.dao.BlogDao;
import com.ljx.blog.entity.Blog;
import com.ljx.blog.queryvo.BlogQuery;
import com.ljx.blog.queryvo.SearchBlog;
import com.ljx.blog.queryvo.ShowBlog;
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


}
