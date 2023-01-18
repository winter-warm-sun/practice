package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<ArticleInfo> getMyList(@Param("uid") Integer uid);

    ArticleInfo getDetail(@Param("aid") Integer aid);

    int update(@Param("aid") Integer aid,
               @Param("uid") Integer uid,
               @Param("title") String title,
               @Param("content") String content);

    int add(@Param("uid") Integer uid,
            @Param("title") String title,
            @Param("content") String content);

    int delete(@Param("aid") Integer aid);

    List<ArticleInfo> getList(@Param("psize") Integer psize,
                              @Param("offset") Integer offset);

    int getTotalCount();
}
