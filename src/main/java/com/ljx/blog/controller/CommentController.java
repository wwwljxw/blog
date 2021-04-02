package com.ljx.blog.controller;

import com.ljx.blog.entity.Comment;
import com.ljx.blog.entity.User;
import com.ljx.blog.queryvo.DetailedBlog;
import com.ljx.blog.service.BlogService;
import com.ljx.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description 评论控制器
 * @author Lin
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * @Description 查询评论列表
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);

        return "blog::commentList";
    }

    @PostMapping("/comments")
    public String addComment(Comment comment, HttpSession session,Model model){
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
//        给评论者设置头像
        if (user != null){
            comment.setAvatar(user.getAvatar());
//            是否为管理员
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }

        Long parentCommentId = comment.getParentComment().getId();
//        当该新增的评论为子评论时
        if (parentCommentId != null){
            comment.setParentCommentId(parentCommentId);
        }
//        保存评论到数据库
        commentService.saveComment(comment);

//        最后重新获取评论列表
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);

        return "blog::commentList";
    }


    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id,Comment comment, RedirectAttributes attributes, Model model){
        commentService.deleteComment(comment,id);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
