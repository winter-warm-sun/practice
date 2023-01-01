package com.example.demo.model;

import lombok.Data;

import java.util.List;

/**
 * 普通实体类
 * (属性与数据库中的userinfo表中的字段一一对应）
 */
@Data
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private String photo;
    private String createtime;
    private String updatetime;
    private int state;
    private List<ArticleInfo> artlist;
}
