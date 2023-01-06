package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    // 根据文章ID获取文章
    public ArticleInfo getArticleById(@Param("id") Integer id);
}
