package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)  {
        // 登录判断业务
        HttpSession session=request.getSession(false);
        if(session!=null&&session.getAttribute("userinfo")!=null) {
            return true;
        }
        log.error("当前用户没有访问权限");
        response.setStatus(401);
        return false;
    }
}
