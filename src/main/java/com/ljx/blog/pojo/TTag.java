package com.ljx.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TTag {
    private Long id;
    private String name;

    private static final long serialVersionUID = 1L;
}
