package com.cc.blog.config;

import com.alibaba.fastjson.JSONObject;
import com.cc.blog.interfaces.GetTokenInterface;
import com.cc.blog.model.User;
import com.cc.blog.model.UserToken;
import com.cc.blog.util.JwtUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cc
 * @date 18-3-26 上午11:07
 */
public class GetTokenImpl implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(GetTokenInterface.class);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        UserToken token = JSONObject.parseObject(JwtUtil.parseJWT(request.getHeader("Authorization"),JwtUtil.SECRET), UserToken.class);
        return token;
    }
}
