package com.ljx.blog.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * null
 * @TableName t_blog_tags
 */
@Data
public class TBlogTags implements Serializable {

    private Integer id;
    private Long tagId;
    private String blogId;

    private static final long serialVersionUID = 1L;
}