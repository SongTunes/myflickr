package com.example.myflickr.config;

import com.example.myflickr.common.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        // 指定controller统一的接口前缀
        // i.e. url前缀
        // 用来区分不同服务
        configurer.addPathPrefix("/myflickr", clazz -> clazz.isAnnotationPresent(RestController.class));

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/myflickr/**")
                .excludePathPatterns("/myflickr/user/login")
                .excludePathPatterns("/myflickr/user/signup");
        // 拦截/myflickr下的所有请求
        // 排除登录注册
    }
}
