package com.cc.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author cc
 * @date 18-3-26 上午11:07
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new GetTokenImpl());
        super.addArgumentResolvers(argumentResolvers);
    }
}
