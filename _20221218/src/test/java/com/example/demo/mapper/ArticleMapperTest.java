package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ArticleMapperTest {
    @Autowired
    private ArticleMapper articleMapper;
    @Test
    void getArticleById() {
        ArticleInfo articleinfo=articleMapper.getArticleById(1);
        log.info(String.valueOf(articleinfo));
    }
}