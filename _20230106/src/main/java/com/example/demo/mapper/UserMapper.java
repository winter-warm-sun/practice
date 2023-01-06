package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper  // MyBatis interface
public interface UserMapper {

    public int add(@Param("username") String username,
                   @Param("password") String password,
                   @Param("photo") String photo);
}
