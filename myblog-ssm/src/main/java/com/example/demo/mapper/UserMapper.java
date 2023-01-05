package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    // 1.根据用户名来查找用户信息（在登陆逻辑中使用）
    User login(@Param("username") String username,@Param("password") String password);

    // 2.根据用户id来找用户信息（博客详情页，就可以根据用户id来查询作者的名字并显示）
    User selectById(@Param("userid") int userid);

}
