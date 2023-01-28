package com.example.demo;

import java.io.File;

public class TestParseTitle {
    public static void main(String[] args) {
        File file=new File("D:\\GitHub\\api\\index.html");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName().substring(0,file.getName().length()-".html".length()));
    }
}
