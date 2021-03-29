package com.ljx.blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.entity.Blog;
import com.ljx.blog.entity.Type;
import com.ljx.blog.entity.User;
import com.ljx.blog.queryvo.BlogQuery;
import com.ljx.blog.queryvo.SearchBlog;
import com.ljx.blog.queryvo.ShowBlog;
import com.ljx.blog.service.BlogService;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
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
        List<BlogQuery> allBlog = blogService.getAllBlogQuery();
        //得到分页结果对象
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allBlog);
        List<Type> allType = typeService.getAllType();

        model.addAttribute("types",allType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    /**
     * @Description 跳转到添加博客页面
     */
    @GetMapping("/blogs/input")
    public String getAddBlog(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog",new Blog());

        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String addBlog(Blog blog, HttpSession session,RedirectAttributes attributes){
//        传递User对象
        blog.setUser((User) session.getAttribute("user"));
//        传递Type对象
        blog.setType(typeService.getType(blog.getType().getId()));
//        传递TypeId
        blog.setTypeId(blog.getType().getId());
//        传递用户id
        blog.setUserId(blog.getUser().getId());
        Date date = new Date();
//        传递创建时间和更新时间
        blog.setCreateTime(date);
        blog.setUpdateTime(date);

        int i = blogService.saveBlog(blog);
        if (i == 0){
            attributes.addFlashAttribute("message","保存成功");
            attributes.addFlashAttribute("message","保存失败，请稍后重试");
        }

        return "redirect:/admin/blogs";
    }


    /**
     * @Description 跳转到修改博客页面
     * @param id  目标博客id
     */
    @GetMapping("/blogs/{id}/input")
    public String getUpdateBlog(@PathVariable long id,Model model){
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();

        model.addAttribute("blog",blogById);
        model.addAttribute("types",allType);

        return "admin/blogs-input";
    }

    /**
     * @Description 更新博客
     */
    @PostMapping("/blogs/{id}")
    public String updateBlog(@PathVariable long id, RedirectAttributes attributes, ShowBlog showBlog){
        showBlog.setUpdateTime(new Date());
        int i = blogService.updateBlog(showBlog);

        if (i == 0){
            attributes.addFlashAttribute("message","修改失败");
        }else {
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * @Description 删除博客
     */
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");

        return "redirect:/admin/blogs";
    }

    /**
     *  搜索博客
     */
    @PostMapping("/blogs/search")
    public String searchBlog(SearchBlog searchBlog, Model model,
                             @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<BlogQuery> blogsBySearch = blogService.getBlogBySearch(searchBlog);
//        对搜索结果进行分页处理
        Page<BlogQuery> blogs = PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> blogQueryPageInfo = new PageInfo<>(blogsBySearch);

        model.addAttribute("pageInfo",blogQueryPageInfo);
        return "admin/blogs :: blogList";
    }
}
