package com.example.demo.controller;

import com.example.demo.searcher.DocSearcher;
import com.example.demo.searcher.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearcherController {
    private static DocSearcher docSearcher=new DocSearcher();

    @RequestMapping("/search")
    public List<Result> search(String query) {
        // 参数是查询词，返回值是响应内容
        List<Result> results=docSearcher.search(query);
        return results;
    }
}
