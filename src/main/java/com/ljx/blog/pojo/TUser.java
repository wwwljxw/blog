package com.ljx.blog.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * null
 * @TableName t_user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUser implements Serializable {

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;

    private List<TBlog> blogs = new ArrayList<>();

    private static final long serialVersionUID = 1L;
}