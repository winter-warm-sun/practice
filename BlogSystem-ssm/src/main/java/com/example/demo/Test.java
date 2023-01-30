package com.example.demo;

import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class Test {
    private static Test test;

    @Resource
    private  UserMapper userMapper;

    @PostConstruct
    public void init() {
        test=this;
        test.userMapper=this.userMapper;
    }

    public static void insert() {
        test.userMapper.add("1","23");
    }
}
