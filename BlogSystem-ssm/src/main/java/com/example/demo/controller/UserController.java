package com.example.demo.controller;

import com.example.demo.model.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
        int result=userService.add(username,password);
        return result;
    }

    @RequestMapping("/login")
    public int login(String username,String password) {
        // 1.非空效验
        if(!StringUtils.hasLength(username)||!StringUtils.hasLength(password)) {
            return 0;
        }
        // 2.进行添加操作
        UserInfo userInfo=userService.login(username,password);
        if(userInfo==null||userInfo.getId()<=0) {
            return 0;
        }else {
            return 1;
        }
    }


}
