package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<ArticleInfo> getMyList(@Param("uid") Integer uid);

    ArticleInfo getDetail(@Param("aid") Integer aid);
}
