package com.cc.blog.config;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author CC
 * @description
 * @create 2018-01-05 16:59
 **/
//切面
@Aspect
@Component
public class RequestLogConfig {

    @Autowired
    private HttpServletRequest request;


    //定义一个切面
    @Pointcut("execution(public * com.cc.blog.controller.*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        String fileuri = joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName();
        log.info2("-->>请求：" + fileuri);
        try {
            if (fileuri.contains("uploadFile")) {
                log.info2("-->>参数" + "文件file类型");
            } else {
                log.info2("-->>参数：" + JSONObject.toJSONString(joinPoint.getArgs()));
            }
        } catch (IllegalStateException i) {
            log.info(i.getMessage());
        }
    }

    @After("log()")
    public void after() {

    }

    @AfterReturning(returning = "map", pointcut = "log()")
    public void returning(Map<String, Object> map) {
        //返回
//        CSLog.info2(JSONObject.toJSONString(map));
    }

//        @AfterThrowing(pointcut = "log()", throwing = "e")
//        public void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws IOException {
//            String uri = joinPoint.getSignature().getDeclaringTypeName() + "." +
//                    joinPoint.getSignature().getName();
//            String content = null;
//            try {
//
//                if (uri.contains("uploadFile")) {
//                    content = "文件file类型";
//                } else {
//                    content = JSONObject.toJSONString(joinPoint.getArgs());
//                }
//            }catch (java.lang.IllegalStateException i){
//                DSLog.info(i.getMessage());
//            }
//            String token = request.getHeader(CSConfig.KEY_AUTHORIZATION);
//            String id = null;
//            try {
//            id = mUserTokenManager.parseTokenStr(token).getUserId();
//
//            String ip = NetworkIpUtil.getClientIpAddr(request);
//            csLogsService.insertSysLogs(PraseUtil.praseInteger(id), "error", content, uri, ip, e.toString());
//        } catch (NullPointerException ne) {
//            CSLog.info(ne.getMessage());
//        }
//    }
}