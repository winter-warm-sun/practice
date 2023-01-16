package com.example.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义登录拦截器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 登录判断业务
        HttpSession session=request.getSession(false);
        if(session!=null&&session.getAttribute("userinfo")!=null) {
            //当前用户已经登录了
            return true;
        }
        log.error("当前用户没有访问权限");
        response.setStatus(401);
        return false;
    }
}
