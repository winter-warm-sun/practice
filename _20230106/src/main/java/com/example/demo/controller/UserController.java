package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/add2")
    @Transactional(propagation = Propagation.REQUIRED)
    public int add2(String username,String password) {
        // 非空效验
        if(!StringUtils.hasLength(username)||!StringUtils.hasLength(password)) {
            return 0;
        }
        int result= userService.add(username,password,null);
        System.out.println("添加影响行数："+result);

        int result2= userService.save(username,password,null);
        System.out.println("添加影响行数："+result2);
        return result;
    }
}
