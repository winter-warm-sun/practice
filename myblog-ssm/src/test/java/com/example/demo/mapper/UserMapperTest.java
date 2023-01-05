package com.example.demo.mapper;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class UserMapperTest {
    @Resource
    private UserMapper userMapper;



    @Test
    void selectById() {
        User user=userMapper.selectById(2);
        log.info(user.toString());
    }
}