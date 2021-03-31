package com.ljx.blog.service.impl;

import com.ljx.blog.entity.Comment;
import com.ljx.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 评论业务层接口实现类
 * @author Lin
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        return null;
    }

    @Override
    public int saveComment(Comment comment) {
        return 0;
    }

    @Override
    public void deleteComment(Comment comment, Long id) {

    }
}
