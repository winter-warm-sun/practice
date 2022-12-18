package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper  // MyBatis interface
public interface UserMapper {
    // 根据用户id查询用户
    public UserInfo getUserById(@Param("id") Integer id);
}
