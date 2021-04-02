package com.ljx.blog.controller;

import com.ljx.blog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @Description 友链页面控制器
 * @author Lin
 */
@Controller
public class FriendsShowController {

    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/friends")
    public String friends(Model model){
        model.addAttribute("friends",friendLinkService.listFriendLink());
        return "friends";
    }
}
