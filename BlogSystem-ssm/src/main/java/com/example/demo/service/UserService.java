package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public int add(String username, String password) {
        return userMapper.add(username,password);
    }

    public UserInfo login(String username, String password) {
        return userMapper.login(username,password);
    }
}
