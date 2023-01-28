package com.example.demo;

import java.io.File;

public class TestParseUrl {
    private static final String INPUT_PATH="D:\\GitHub\\api";
    public static void main(String[] args) {
        File file=new File("D:\\GitHub\\api\\index.html");
        String part1 = "https://docs.oracle.com/javase/8/docs/api/";
        String part2=file.getAbsolutePath().substring(INPUT_PATH.length());
        String result=part1+part2;
        System.out.println(result);
    }
}
