package com.ljx.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.entity.Type;
import com.ljx.blog.queryvo.FirstPageBlog;
import com.ljx.blog.service.BlogService;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description 前台分类展示博客控制类
 * @author Lin
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model){
        List<Type> types = typeService.getAllTypeAndBlog();

//        id为-1表示从首页导航栏点击进入分类页面
        if (id == -1){
            types.get(0).getId();
        }
        model.addAttribute("types",types);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);

        PageHelper.startPage(pageNum, 10000);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
