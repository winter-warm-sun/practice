package com.example._20221213.model;

import lombok.Data;

@Data // 组合注解：getter()+setter()+toString()
public class User {
    private Integer id;
    private String username;
    private String password;
    private int age;
}
