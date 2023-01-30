package com.example.demo;

import com.example.demo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Test {
    @Autowired
    private static TestMapper testMapper;

    public static void insert() {
        testMapper.saveForwardIndex(1,"","","");
    }


}
