package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IndexMapper {

    void saveForwardIndex(@Param("docid") int docid,
                          @Param("title") String title,
                          @Param("url") String url,
                          @Param("content") String content);
}
