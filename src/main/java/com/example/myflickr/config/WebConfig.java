package com.example.myflickr.config;

import com.example.myflickr.common.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtInterceptor jwtInterceptor;

    // 自动从application配置文件中读取
//    @Value("${file-save-path}")
    // 读取不行 可能不是yml
    private String fileSavePath;

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
                // .excludePathPatterns("/myflickr/photo/**");
        // 拦截/myflickr下的所有请求
        // 排除登录注册
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 配置资源映射
         * 如果访问的资源路径以"/photo/"开头
         * 则映射到服务端的fileSavePath="/home/myflickr/photo/"这个文件夹
         */
        registry.addResourceHandler("/myflickr/photo/**")
                .addResourceLocations("file:" + "/home/myflickr/photo/");
    }

}
