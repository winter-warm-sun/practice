package com.example.demo.controller;

import com.example.demo.common.Constant;
import com.example.demo.common.SecurityUtil;
import com.example.demo.common.SessionUtil;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/reg")
    public int reg(String username,String password) {
        // 1.非空效验
        if(!StringUtils.hasLength(username)||!StringUtils.hasLength(password)) {
            return 0;
        }
        // 2.进行添加操作
        int result=userService.add(username, SecurityUtil.encrypt(password));// 密码加盐
        return result;
    }

    @RequestMapping("/login")
    public int login(HttpServletRequest request,String username,String password) {
        // 1.非空效验
        if(!StringUtils.hasLength(username)||!StringUtils.hasLength(password)) {
            return 0;
        }
        // 2.进行查询操作
        UserInfo userInfo=userService.login(username);
        if(userInfo==null||userInfo.getId()<=0) {// userinfo 无效
            return -1;
        }else {
            boolean result=SecurityUtil.decrypt(password,userInfo.getPassword());
            if(result) {
                HttpSession session= request.getSession();
                session.setAttribute(Constant.SESSION_USERINFO_KEY,userInfo);
                return 1;
            }
        }
        return -1;
    }

    @RequestMapping("/myinfo")
    public UserInfo myInfo(HttpServletRequest request) {
        return SessionUtil.getLoginUser(request);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        HttpSession session= request.getSession(false);
        if(session!=null&&
                session.getAttribute(Constant.SESSION_USERINFO_KEY)!=null) {
            //移除session中当前登录的用户
            session.removeAttribute(Constant.SESSION_USERINFO_KEY);
        }
        return true;
    }
}
