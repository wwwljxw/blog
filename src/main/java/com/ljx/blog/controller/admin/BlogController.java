package com.ljx.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.entity.Type;
import com.ljx.blog.queryvo.BlogQuery;
import com.ljx.blog.queryvo.ShowBlog;
import com.ljx.blog.service.BlogService;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description 博客管理控制器
 * @author Lin
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    /**
     * @Description 跳转到博客管理页面并分页查询所有博客
     */
    @GetMapping("/blogs")
    public String getBlogs(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 10);
        List<BlogQuery> allType = blogService.getAllBlogQuery();
        //得到分页结果对象
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allType);

        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    @GetMapping("/blogs/{id}/input")
    public String getUpdateBlog(@PathVariable long id,Model model){
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();

        model.addAttribute("blog",blogById);
        model.addAttribute("types",allType);

        return "admin/blogs-input";
    }
}
