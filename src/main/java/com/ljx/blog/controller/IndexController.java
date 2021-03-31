package com.ljx.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.queryvo.DetailedBlog;
import com.ljx.blog.queryvo.FirstPageBlog;
import com.ljx.blog.queryvo.RecommendBlog;
import com.ljx.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * @Description 分页展示所有博客和推荐博客
     */
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

    /**
     *  @Description 搜索博客
     */
    @PostMapping("/search")
    public String searchBlog(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                             Model model,String query){
        PageHelper.startPage(pageNum,10);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);

        return "search";
    }

    /**
     * @Description 博客信息统计
     */
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        int blogTotal = blogService.getBlogTotal();
        int blogViewTotal = blogService.getBlogViewTotal();
        int blogCommentTotal = blogService.getBlogCommentTotal();
        int blogMessageTotal = blogService.getBlogMessageTotal();

        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "index :: blogMessage";
    }

    /**
     * @Description 查看博客详情
     * @param id 要查询的博客的id
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable long id,Model model){
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        model.addAttribute("blog",detailedBlog);

        return "blog";
    }

}
