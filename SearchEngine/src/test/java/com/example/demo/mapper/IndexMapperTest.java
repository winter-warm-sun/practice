package com.example.demo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IndexMapperTest {

    @Resource
    private IndexMapper indexMapper;
    @Test
    void saveForwardIndex() {
        indexMapper.saveForwardIndex(1,"88","klkl","awdaw");
    }
}