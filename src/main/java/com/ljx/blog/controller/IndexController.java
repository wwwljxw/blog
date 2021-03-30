package com.ljx.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.queryvo.FirstPageBlog;
import com.ljx.blog.queryvo.RecommendBlog;
import com.ljx.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description 首页控制器
 * @author Lin
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,10);
//        获取所有博客
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
//        获取推荐博客
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
//        给所有博客的的查询结果分页
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlogs", recommendedBlog);

        return "index";
    }

}
