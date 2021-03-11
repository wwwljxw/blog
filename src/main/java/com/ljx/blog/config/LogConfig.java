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
public class LogConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.ljx.blog.controller.*.*(..))")   //    定义切入点
    public void log(){
    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        获得uri、ip
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
//        获得类名、方法名、方法参数
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

//        封装并输出到日志
        RequestLog requestLog = new RequestLog(uri, ip, classMethod);
        logger.info("Request {}",requestLog);
    }
//    @After("log()")
//    public void doAfter(){
//        log.info("----------doAfter--------");
//    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){

        //打印返回值
        logger.info("Result: {}", result);
    }

    @Data
    @AllArgsConstructor
    class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
    }
}
