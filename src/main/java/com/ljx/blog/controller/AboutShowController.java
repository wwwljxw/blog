package com.ljx.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description 关于我页面控制器
 * @author Lin
 */
@Controller
public class AboutShowController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
