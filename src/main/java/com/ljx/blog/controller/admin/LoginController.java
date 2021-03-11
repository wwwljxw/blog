package com.ljx.blog.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author linjx
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @GetMapping
    public String getLogin(){
        return "/admin/login";
    }

    @PostMapping (value = "/login")
    public String login(String username, String password, RedirectAttributes attributes){

        System.out.println("登录执行");
//        获取当前用户
        Subject subject = SecurityUtils.getSubject();
//        封装用户登录的数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }catch (Exception e){
            attributes.addFlashAttribute("msg","用户名或密码错误");
            return "redirect:/admin";
        }
        return "admin/index";
    }
}
