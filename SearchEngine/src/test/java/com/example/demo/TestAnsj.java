package com.example.demo;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.List;

public class TestAnsj {
    public static void main(String[] args) {
        String s="今天的中午饭真好吃";
        // Term 就表示一个分词结果.
        List<Term> terms = ToAnalysis.parse(s).getTerms();
        for (Term term : terms) {
            System.out.println(term.getName());
        }
    }
}
