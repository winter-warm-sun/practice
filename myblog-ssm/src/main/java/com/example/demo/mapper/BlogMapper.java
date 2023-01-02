package com.example.demo.mapper;

import com.example.demo.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {
    // 1.往博客列表里，插入一个博客
    int insert(Blog blog);

    // 2.获取博客列表中的所有博客信息
    List<Blog> selectAll();

    // 3.根据博客ID获取指定的博客内容
    Blog selectOne(@Param("blogid") int blogid);

    // 4.从博客列表中，根据博客id删除博客
    int delete(int blogid);
}
