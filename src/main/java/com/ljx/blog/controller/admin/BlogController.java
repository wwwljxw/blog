package com.ljx.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ljx.blog.pojo.TBlog;
import com.ljx.blog.service.BlogsService;
import com.ljx.blog.service.TagService;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class BlogController{

    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogsService blogsService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    /**
     *  查询所有博客
     */
    @RequestMapping("/blogs")
    public String getBlogs(@RequestParam(value = "pagenum", defaultValue = "1") int pagenum, Model model){
        PageInfo<TBlog> allBlog = blogsService.getAllBlog(pagenum, 5);
        model.addAttribute("pageInfo", allBlog);
        setTypeAndTag(model);
        return "admin/blogs";
    }

    /**
     *  按条件查询博客
     */
    @RequestMapping("/blogs/search")
    public String searchBlogs(@RequestParam(value = "pagenum", defaultValue = "1") int pagenum,TBlog blog, Model model){
        PageInfo<TBlog> tBlogPageInfo = blogsService.searchAllBlog(pagenum, 5, blog);
        model.addAttribute("pageInfo", tBlogPageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }
}
