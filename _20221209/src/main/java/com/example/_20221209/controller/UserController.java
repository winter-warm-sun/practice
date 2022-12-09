package com.example._20221209.controller;
import com.example._20221209.model.ReadList;
import com.example._20221209.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;


import javax.annotation.PostConstruct;

@Controller
public class UserController {
    @Value("${string1}")
    private String string1;

    @Value("${string2}")
    private String string2;

    @Value("${string3}")
    private String string3;

    @Autowired
    private Student student;

    @Autowired
    private ReadList readList;

    @PostConstruct
    public void UserController(){
        System.out.println("readList:"+readList);
    }
}
