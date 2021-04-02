package com.ljx.blog.controller;

import com.ljx.blog.queryvo.BlogQuery;
import com.ljx.blog.queryvo.FirstPageBlog;
import com.ljx.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Description 时间轴显示博客控制类
 * @author Lin
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        List<BlogQuery> blogs = blogService.getAllBlogQuery();
        model.addAttribute("blogs", blogs);
        return "archives";
    }
}
