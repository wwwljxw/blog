package com.ljx.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @RequestMapping("/blogs")
    public String getBlogs(){
        return "admin/blogs";
    }
}
