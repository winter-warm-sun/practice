package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    // 根据全名查询用户对象（非模糊查询）
    public UserInfo getUserByFullName(@Param("username") String username);

    //获取列表，根据创建时间进行倒叙或正序
    public List<UserInfo> getOrderList(@Param("order")String order);

    // 登录功能
    public UserInfo login(@Param("username") String username,
                          @Param("password") String password);

    // 根据名称进行模糊查询
    public List<UserInfo> getListByName(@Param("username") String username);

}
