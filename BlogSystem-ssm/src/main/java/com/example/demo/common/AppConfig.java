package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 将拦截器加入系统配置
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    // 不拦截的url集合
    List<String> excludes=new ArrayList<String>(){{
        add("/**/*.html");//任何路径下的html文件
        add("/js/**");
        add("/editor.md/**");
        add("/css/**");
        add("/img/**");
        add("/user/login");//登录接口
        add("/user/reg");//注册接口
        add("/art/detail"); // 放行文章详情接口
        add("/art/list"); // 放行文章分页列表接口
        add("/art/totalpage"); // 放行文章分页总页数接口
    }};

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置拦截器
        InterceptorRegistration registration=
                registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(excludes);
    }
}
