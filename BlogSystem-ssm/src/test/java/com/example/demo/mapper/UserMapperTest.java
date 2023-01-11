package com.example.demo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Transactional
    @Test
    void add() {
        int result=userMapper.add("admin","admin");
        System.out.println(result);
    }
}