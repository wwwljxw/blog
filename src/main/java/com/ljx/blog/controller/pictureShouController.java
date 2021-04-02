package com.ljx.blog.controller;

import com.ljx.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description 照片墙页面控制器
 * @author Lin
 */
@Controller
public class pictureShouController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture")
    public String picture(Model model){
        model.addAttribute("picture",pictureService.listPicture());
        return "picture";
    }
}
