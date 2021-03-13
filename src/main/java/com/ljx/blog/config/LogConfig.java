package com.ljx.blog.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lin
 */

@Aspect
@Component
@Slf4j
public class LogConfig {

    @Pointcut("execution(* com.ljx.blog.controller..*.*(..))")        //定义切入点表达式
    public void log(){}

    @Before("log()")    //引用切入点
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获得类名.方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获得方法参数
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        //打印请求信息
        log.info("Request: {}", requestLog);
    }


    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){

        //打印返回值
        log.info("Result: {}", result);
    }

    @Data
    @AllArgsConstructor
    public class RequestLog{      //用于封装请求信息
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}
