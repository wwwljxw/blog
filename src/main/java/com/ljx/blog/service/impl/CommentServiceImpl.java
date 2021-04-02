package com.ljx.blog.service.impl;

import com.ljx.blog.dao.BlogDao;
import com.ljx.blog.dao.CommentDao;
import com.ljx.blog.entity.Comment;
import com.ljx.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lin
 * @Description 评论业务层接口实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private BlogDao blogDao;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();


    /**
     * @param blogId 博客id
     * @Description 查询所有评论
     */
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
//        查询出父评论
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
//            获取父评论编号
            Long id = comment.getId();
//            获取父评论者的昵称
            String parentNickname = comment.getNickname();
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
//            查询子评论
            combineChildren(blogId, childComments, parentNickname);
            comment.setReplyComments(tempReplys);
//            刷新子评论容器
            tempReplys = new ArrayList<>();
        }
        return comments;
    }


    /**
     * @param blogId          博客id
     * @param childComments   子评论
     * @param parentNickname1 父评论昵称
     * @Description 查询所有子评论
     */
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
//        判断是否有一级子评论
        if (childComments.size() > 0) {
//            如果有的话，寻找出子评论id
            for (Comment comment : childComments) {
                //                获取子评论留言者的昵称
                String nickname = comment.getNickname();
//                写入父留言者的昵称
                comment.setParentNickname(parentNickname1);
//                获取子评论id
                Long id = comment.getId();
//                将查询到的子评论存放到tempReplys数组中
                tempReplys.add(comment);
//                查询二级子评论
                recursively(blogId, id, nickname);
            }
        }
    }

    /**
     * @param blogId          博客id
     * @param childId         子评论id
     * @param parentNickname1 子评论留言者的昵称
     * @Description 循环迭代找出子集回复
     */
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> comments = commentDao.findByBlogIdAndReplayId(blogId, childId);

//        如果有子评论的话
        if (comments.size() > 0) {
            for (Comment comment : comments) {
//                获取子评论留言者的昵称
                String nickname = comment.getNickname();
//                写入父留言者的昵称
                comment.setParentNickname(parentNickname1);
//                获取子评论id
                Long id = comment.getId();
//                将查询到的子评论存放到tempReplys数组中
                tempReplys.add(comment);
                recursively(blogId, childId, nickname);
            }
        }
    }

    /**
     * @param comment 评论内容
     * @Description 添加保存评论
     */
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int i = commentDao.saveComment(comment);
//        查询出文章评论数量并更新
        blogDao.getCommentCountById(comment.getBlogId());
        return i;
    }

    /**
     * @Description 删除评论
     */
    @Override
    public void deleteComment(Comment comment, Long id) {

        commentDao.deleteComment(id);
        //        查询出文章评论数量并更新
        blogDao.getCommentCountById(comment.getBlogId());
    }
}
