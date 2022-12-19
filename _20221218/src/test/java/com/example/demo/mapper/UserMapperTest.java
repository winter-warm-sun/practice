package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
// 表示当前单元测试运行在Spring Boot环境中
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void getUserById() {
        UserInfo userInfo=userMapper.getUserById(1);
        System.out.println(userInfo);
    }

//    @Transactional
    @Test
    void add() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("王五");
        userInfo.setPassword("123");
        userInfo.setPhoto("default.png");
        int result=userMapper.add(userInfo);
        Assertions.assertEquals(1,result);
    }
    @Transactional
    @Test
    void add2() {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("老六");
        userInfo.setPassword("123");
        userInfo.setPhoto("default.jpg");
        System.out.println("添加之前user id:"+userInfo.getId());
        int result=userMapper.add2(userInfo);
        System.out.println("受影响的行数："+result);
        System.out.println("添加之后 user id:"+userInfo.getId());
        Assertions.assertEquals(1,result);
    }
//    @Transactional
    @Test
    void delete() {
        int result=userMapper.delete(7);
        System.out.println("受影响的函数:"+result);
        Assertions.assertEquals(1,result);
    }

//    @Transactional
    @Test
    void update() {
        int result=userMapper.update(4,"张三");
        Assertions.assertEquals(1,result);
    }
}