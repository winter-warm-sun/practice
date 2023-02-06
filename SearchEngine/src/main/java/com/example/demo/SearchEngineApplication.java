package com.example.demo;

import com.example.demo.searcher.DocSearcher;
import com.example.demo.searcher.Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SearchEngineApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SearchEngineApplication.class, args);
        DocSearcher docSearcher=new DocSearcher();
    }

}
