package com.example.demo.searcher;

import lombok.Data;

@Data
public class Result {
    private String title;
    private String url;
    // desc是正文的一段摘要
    private String desc;
}
