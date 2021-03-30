package com.ljx.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljx.blog.entity.FriendLink;
import com.ljx.blog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Description 友链管理控制器
 * @author Lin
 */
@Controller
@RequestMapping("/admin")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;


    /**
     * @Description 跳转到友链页面，并分页查询友联列表
     */
    @GetMapping("/friendlinks")
    public String getFriendLink(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 10);
        List<FriendLink> allLink = friendLinkService.listFriendLink();
        //得到分页结果对象
        PageInfo<FriendLink> pageInfo = new PageInfo<>(allLink);

        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendlinks";
    }

    /**
     * @Description 跳转到修改友链页面
     */
    @GetMapping("/friendlinks/{id}/input")
    public String getUpdateFriendLink(Model model, @PathVariable long id){
        model.addAttribute("friendlink", friendLinkService.getFriendLink(id));
        return "admin/friendlinks-input";
    }

    @PostMapping("/friendlinks/{id}")
    public String updateFriendLink(FriendLink friendLink, RedirectAttributes attributes){
        int i = friendLinkService.updateFriendLink(friendLink);
        if (i == 0){
            attributes.addFlashAttribute("message","编辑失败");
        }else {
            attributes.addFlashAttribute("message","编辑成功");
        }
        return "redirect:/admin/friendlinks";
    }

    /**
     * @Description 跳转到新增友链页面
     */
    @GetMapping("/friendlinks/input")
    public String getAddFriendLink(Model model){
        model.addAttribute("friendlink",new FriendLink());
        return "admin/FriendLinks-input";
    }

    @PostMapping("/friendlinks")
    public String addFriendLink(FriendLink friendLink, RedirectAttributes attributes){
        FriendLink friendLinkByName = friendLinkService.getFriendLinkByBlogaddress(friendLink.getBlogaddress());
        if (friendLinkByName != null){
            attributes.addFlashAttribute("message","该分类已存在，不能重复添加");
            return "redirect:/admin/friendlinks/input";
        }

        int i = friendLinkService.saveFriendLink(friendLink);
        if (i == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/friendlinks";
    }

    @GetMapping("/friendlinks/{id}/delete")
    public String deleteFriendLink(@PathVariable long id, RedirectAttributes attributes){
        friendLinkService.deleteFriendLink(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/friendlinks";
    }
}
