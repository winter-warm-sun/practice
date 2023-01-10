package com.example.demo.model;

import lombok.Data;

@Data
public class Article {
    private int id;
    private String title;
    private String content;
    private String createtime;
    private String updatetime;
    private int uid;
    private int rcount; // 阅读量
    private int state;
}
