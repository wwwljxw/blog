package com.ljx.blog.controller;

import com.ljx.blog.Exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Lin
 */
@Controller
public class IndexController {

    @GetMapping("/types")
    public String getTypes(){
        return "types";
    }
}
