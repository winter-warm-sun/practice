package com.example.demo.service;

import com.example.demo.mapper.BlogMapper;
import com.example.demo.model.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogService {
    @Resource
    private BlogMapper blogMapper;

    public List<Blog> selectAll() {
        List<Blog> blogs=blogMapper.selectAll();
        return blogs;
    }

    public Blog selectOne(int blogid) {
        Blog blog=blogMapper.selectOne(blogid);
        return blog;
    }
}
