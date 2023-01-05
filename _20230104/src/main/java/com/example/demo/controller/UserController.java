package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @RequestMapping("/login")
    public boolean login(HttpServletRequest request,
                         String username,String password) {
        // 1.非空判断
        if(StringUtils.hasLength(username)&&StringUtils.hasLength(password)) {
            // 2.验证用户名和密码是否正确
            if("admin".equals(username)&&"admin".equals(password)) {
                // 登录成功
                HttpSession session=request.getSession();
                session.setAttribute("userinfo","admin");
                return true;
            }else {
                // 用户名或密码输入错误
                return false;
            }
        }
        return false;
    }

    @RequestMapping("/getinfo")
    public String getInfo() {
        log.debug("执行了 getinfo 方法");
        return "执行了 getinfo 方法";
    }

    @RequestMapping("/reg")
    public String reg() {
        log.debug("执行了 reg 方法");
        return "执行了 reg 方法";
    }
}
