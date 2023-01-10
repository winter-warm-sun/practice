package com.example.demo.mapper;

import com.example.demo.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BlogMapperTest {
    @Autowired
    private BlogMapper blogMapper;

    @Transactional
    @Test
    void insert() {
        Blog blog = new Blog();
        blog.setTitle("666");
        blog.setContent("666");
        blog.setUserid(1);
        int ret = blogMapper.insert(blog);
        Assertions.assertEquals(1, ret);
    }

    @Transactional
    @Test
    void selectOne() {
        Blog blog = blogMapper.selectOne(1);
        log.info(String.valueOf(blog));
    }

    @Test
    void selectAll() {
        List<Blog> list = blogMapper.selectAll();
        for (Blog blog : list) {
            String content = blog.getContent();
            if (content.length() > 50) {
                content = content.substring(0, 50) + "...";
            }
            blog.setContent(content);
        }
        log.info(list.toString());
    }

    @Transactional
    @Test
    void delete() {
        int result = blogMapper.delete(5);
        Assertions.assertEquals(1, result);
    }
}