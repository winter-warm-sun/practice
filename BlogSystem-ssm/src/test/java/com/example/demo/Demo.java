package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Demo {
    @Autowired
    private static Test test;
    public static void main(String[] args) {
        test.insert();
    }
}
