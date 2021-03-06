package com.ljx.blog.queryvo;



import com.ljx.blog.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 查询博客实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {

    private Long id;
    private String title;
    private Date updateTime;
    private Date createTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;
}