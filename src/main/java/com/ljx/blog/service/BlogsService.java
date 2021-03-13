package com.ljx.blog.service;

import com.github.pagehelper.PageInfo;
import com.ljx.blog.pojo.TBlog;


/**
 * @author linjx
 */
public interface BlogsService {

    /**
     *  后台展示博客
     */
    TBlog getBlog(Long id);

    /**
     *  后台展示博客列表
     */
    PageInfo<TBlog> getAllBlog(int page, int offset);

    /**
     *  后台根据标题、分类、推荐搜索博客
     */
    PageInfo<TBlog> searchAllBlog(int page, int offset,TBlog blog);

    /**
     *  后台新增博客
     */
    int addBlog(TBlog blog);

    /**
     *  后台更新博客
     */
    int updateBlog(TBlog blog);

    /**
     *  后台删除博客
     */
    int deleteBlog(Long id);
}
