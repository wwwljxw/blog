package com.ljx.blog.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * null
 * @TableName t_tag
 */
@Data
public class TTag implements Serializable {

    private Long id;
    private String name;

    private static final long serialVersionUID = 1L;
}