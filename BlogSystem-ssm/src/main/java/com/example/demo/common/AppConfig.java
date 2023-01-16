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
