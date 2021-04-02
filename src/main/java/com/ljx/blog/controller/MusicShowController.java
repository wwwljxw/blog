package com.ljx.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description 音乐盒页面控制器
 * @author Lin
 */
@Controller
public class MusicShowController {

    @GetMapping("/music")
    public String music(){
        return "music";
    }
}
