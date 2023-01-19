package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int add(@Param("username") String username,@Param("password") String password);

    UserInfo login(@Param("username") String username);
}
