package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
public class Blog {
    private int blogid;
    private String title;
    private String content;
    private int userid;
    private Timestamp posttime;

    //返回格式化好的时间
    public String getPostTime() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(posttime);
    }
}
