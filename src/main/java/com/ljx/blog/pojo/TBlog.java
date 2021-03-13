package com.ljx.blog.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * null
 * @TableName t_blog
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBlog implements Serializable {

    private Long id;

    /**
     * 标题
     * @mbg.generated 2021-03-07 18:20:26
     */
    private String title;

    /**
     * 博客内容
     * @mbg.generated 2021-03-07 18:20:26
     */
    private String content;

    /**
     * 首图
     * @mbg.generated 2021-03-07 18:20:26
     */
    private String firstPicture;

    /**
     * 标记
     * @mbg.generated 2021-03-07 18:20:26
     */
    private String flag;

    /**
     * 浏览次数
     * @mbg.generated 2021-03-07 18:20:26
     */
    private Integer views;

    /**
     * 赞赏开启
     * @mbg.generated 2021-03-07 18:20:26
     */
    private boolean appreciation;

    /**
     * 版权开启
     * @mbg.generated 2021-03-07 18:20:26
     */
    private boolean shareStatement;


    /**
     * 评论开启
     * @mbg.generated 2021-03-07 18:20:26
     */
    private boolean commentabled;

    /**
     * 是否发布
     * @mbg.generated 2021-03-07 18:20:26
     */
    private boolean published;

    /**
     * 是否推荐
     * @mbg.generated 2021-03-07 18:20:26
     */
    private boolean recommend;

    /**
     * 创建时间
     * @mbg.generated 2021-03-07 18:20:26
     */
    private Date createTime;

    /**
     * 更新时间
     * @mbg.generated 2021-03-07 18:20:26
     */
    private Date updateTime;

    private TType type;

    private TUser user;

    /**
     * 分类id
     */
    private Long typeId;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 博客简介
     */
    private String description;

    /**
     * 标签id
     * @mbg.generated 2021-03-07 18:20:26
     */
    private String tagIds;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_blog
     *
     * @mbg.generated 2021-03-07 18:20:26
     */
    private static final long serialVersionUID = 1L;
}