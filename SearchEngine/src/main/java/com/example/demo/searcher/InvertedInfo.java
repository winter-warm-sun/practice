package com.example.demo.searcher;

import lombok.Data;

@Data
public class InvertedInfo {
    private int id;
    private String word;
    private int docid;
    private int weight;
}
