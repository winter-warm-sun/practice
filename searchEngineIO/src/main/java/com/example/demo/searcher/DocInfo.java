package com.example.demo.searcher;

import lombok.Data;

@Data
public class DocInfo {
    private int docid;
    private String title;
    private String url;
    private String content;
}