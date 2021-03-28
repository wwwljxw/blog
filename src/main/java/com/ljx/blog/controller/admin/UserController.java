package com.ljx.blog.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Lin
 * @Description 用户登录控制器
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    /**
     * @Description 跳转到登录页面
     */
    @GetMapping
    public String getLogin() {
        return "admin/login";
    }

    /**
     * @Description 登录校验
     * @param username  用户名
     * @param password  密码
     * @param attributes  返回页面消息
     * @return  登录成功跳转登录成功页面，登录失败返回登录页面
     */
    @PostMapping("/login")
    public String login(String username, String password, RedirectAttributes attributes) {
//        获取当前用户
        Subject subject = SecurityUtils.getSubject();
//        封装用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
        return "admin/index";
    }

    /**
     * @Description 注销
     * @return  返回登录页面
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
