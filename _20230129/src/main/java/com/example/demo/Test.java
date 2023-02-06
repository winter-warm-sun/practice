package com.example.demo;

import com.example.demo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Test {
    private static Test test;

    @Autowired
    private TestMapper testMapper;

    @PostConstruct
    public void init() {
        test=this;
        test.testMapper=this.testMapper;
    }
    public static void insert() {
        test.testMapper.saveForwardIndex(1,"","","");
    }
}
