package com.ljx.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.Mapper.TBlogMapper;
import com.ljx.blog.pojo.TBlog;
import com.ljx.blog.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linjx
 */
@Service
public class BlogsServiceImpl implements BlogsService {

    @Autowired
    private TBlogMapper tBlogMapper;

    @Override
    public TBlog getBlog(Long id) {
        return tBlogMapper.getBlog(id);
    }

    @Override
    public PageInfo<TBlog> getAllBlog(int page, int offset) {
        PageHelper.startPage(page,offset);
        List<TBlog> allBlog = tBlogMapper.getAllBlog();
        return new PageInfo<TBlog>(allBlog);
    }

    @Override
    public PageInfo<TBlog> searchAllBlog(int page, int offset,TBlog blog) {
        PageHelper.startPage(page,offset);
        List<TBlog> tBlogs = tBlogMapper.searchAllBlog(blog);
        return new PageInfo<TBlog>(tBlogs);
    }

    @Override
    public int addBlog(TBlog blog) {
        return tBlogMapper.saveBlog(blog);
    }

    @Override
    public int updateBlog(TBlog blog) {
        return 0;
    }

    @Override
    public int deleteBlog(Long id) {
        return 0;
    }
}
