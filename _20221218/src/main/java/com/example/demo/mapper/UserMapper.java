package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper  // MyBatis interface
public interface UserMapper {
    // 根据用户id查询用户
    public UserInfo getUserById(@Param("id") Integer id);

    // 添加用户，返回受影响的行数
    public int add(UserInfo userInfo);

    // 添加用户，返回自增ID
    public int add2(UserInfo userInfo);

    // 删除用户，返回受影响的行数
    int delete(@Param("id") Integer id);

    // 修改用户【根据ID修改名称】
    int update(@Param("id")Integer id,@Param("username") String username);
}
