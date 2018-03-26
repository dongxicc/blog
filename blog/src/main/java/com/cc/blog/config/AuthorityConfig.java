package com.cc.blog.config;

/**
 * 自定义拦截器，判断此次请求是否有权限
 * 只是自己测试的一个大概
 *
 * @author cc
 * @date 18-3-22 上午10:40
 */

import com.alibaba.fastjson.JSONObject;
import com.cc.blog.interfaces.Authority;
import com.cc.blog.model.UserToken;
import com.cc.blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色拦截
 */
@Component
public class AuthorityConfig extends HandlerInterceptorAdapter {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out;
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        Authority authority = method.getAnnotation(Authority.class);
        if (authority == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        if (authority.role().length > 0) {
            // 如果权限配置不为空, 则取出配置值
            String[] authorities = authority.role();
            Set<String> authSet = new HashSet<>();
            for (String auth : authorities) {
                // 将权限加入一个set集合中
                authSet.add(auth);
            }
            //TODO 对权限进行细分
            // 这里我为了方便是直接参数传入权限, 在实际操作中应该是从参数中获取用户Id
            // 到数据库权限表中查询用户拥有的权限集合, 与set集合中的权限进行对比完成权限校验
            String tokenStr = request.getHeader("Authorization");
            UserToken userToken;
            try {
                userToken = JSONObject.parseObject(JwtUtil.parseJWT(tokenStr, JwtUtil.SECRET), UserToken.class);
            } catch (Exception e) {
                JSONObject res = new JSONObject();
                res.put("status", "3");
                res.put("result", "你还未登陆，非法请求");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
            String token = (String) redisTemplate.opsForValue().get(userToken.getId());
            if (token == null || tokenStr != token) {
                JSONObject res = new JSONObject();
                res.put("status", "3");
                res.put("result", "登录状态已失效，请重新登陆");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }

            String[] roleStr = userToken.getRole().split(",");
            Set<String> roleSet = new HashSet<>();
            for (String role : roleStr) {
                // 将权限加入一个set集合中
                roleSet.add(role);
            }
            Set<String> result = new HashSet<>();
            result.clear();
            result.addAll(roleSet);
            result.retainAll(authSet);
            if (result != null && !result.isEmpty()) {
                return true;
            }
        }
        // 拦截之后应该返回公共结果, 这里没做处理
        return false;
    }
}