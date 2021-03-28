package com.ljx.blog.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Lin
 * 拦截所有controller发出的异常，并对异常进行统一处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler( HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request url : {}, Exception : {}",request.getRequestURI(),e);

        //不处理带有状态码的异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView model = new ModelAndView();
        model.addObject("url",request.getRequestURI());
        model.addObject("exception",e);
        model.setViewName("error/error");
        return model;
    }


}
