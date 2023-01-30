package com.example.demo.searcher;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Component
public class DocSearcher {
    private Index index=new Index();

    // 在构造该类时，完成索引的加载
    public DocSearcher() {
        index.load();
    }

    // 根据查询词，进行搜索，得到搜索结果集合（先通过查询词进行倒排搜索）
    // 结果集合中包含若干条记录，每个记录中包含搜索结果的标题、描述、URL（再根据查找到的结果进行正排搜索）

    public List<Result> search(String query) {
        // 1.[分词] 对查询词进行分词
        List<Term> terms= ToAnalysis.parse(query).getTerms();
        // 2.[触发] 对每个分词结果查找倒排索引，得到一个倒排拉链
        System.out.println("分词结果为："+terms);
        ArrayList<Weight> allTokenResult=new ArrayList<>();
        for (Term term:terms) {
            String word=term.getName();
            // 查倒排拉链
            ArrayList<Weight> invertedList=index.getInverted(word);

            // 倒排拉链可能找不到，这个词可能就在索引中根本不存在
            if(invertedList==null) {
                continue;
            }
            allTokenResult.addAll(invertedList);
        }
        // 3.[排序] 针对结果集合进行排序，按照权重降序排列
        allTokenResult.sort(new Comparator<Weight>() {
            @Override
            public int compare(Weight o1, Weight o2) {
                // 降序排列
                return o2.getWeight()-o1.getWeight();
            }
        });
        // 4.[返回] 构造返回结果
        ArrayList<Result> results=new ArrayList<>();
        for (Weight weight:allTokenResult) {
            DocInfo docInfo=index.getDocInfo(weight.getDocId());
            Result result=new Result();
            result.setTitle(docInfo.getTitle());
            result.setUrl(docInfo.getUrl());
            result.setDesc(GenDesc(docInfo.getContent(),terms));
            results.add(result);
        }
        return results;
    }

    // 正文摘要实现
    private String GenDesc(String content, List<Term> terms) {
        // 用分词结果中的第一个在描述中找到的词，作为位置的中心
        int firstPos=-1;
        for (Term term:terms) {
            String firstWord=term.getName();
            firstPos=content.toLowerCase().indexOf(" "+firstWord+" ");// 保证该单词必须是独立的单词
            if(firstPos>0) {
                break;
            }
        }
        // 如果所有的分词结果在正文中都不存在，则直接返回空的描述
        if(firstPos==-1) {
            return "";
        }
        /* 直接截取firstPos周围的文本
        从firstPos往前找60个字符作为描述开始
        然后从描述开始位置往后找160个字符作为整个描述
        （60与160都是自定义的）
         */
        String desc="";
        int descBeg=firstPos<60 ? 0:firstPos-60;
        if(descBeg+160>content.length()) {
            desc=content.substring(descBeg);
        }else {
            // 正文长度充足，在最后加上...
            desc=content.substring(descBeg,descBeg+160)+"...";
        }
        return desc;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        DocSearcher docSearcher=new DocSearcher();
        while (true) {
            System.out.println("-> ");
            String query=scanner.next();
            List<Result> results=docSearcher.search(query);
            for (Result result:results) {
                System.out.println("=========================");
                System.out.println(result);
            }
        }
    }
}
