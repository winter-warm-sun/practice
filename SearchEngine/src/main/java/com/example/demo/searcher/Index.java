package com.example.demo.searcher;

import com.example.demo.mapper.IndexMapper;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class Index {
    public static final String INPUT_PATH="D:\\GitHub\\api";

    @Autowired
    private IndexMapper indexMapper;

    // 正排索引,下标对应docId
    private ArrayList<DocInfo> forwardIndex=new ArrayList<>();

    // 倒排索引，key是分词结果，value是这个分词term对应的倒排拉链（包含一堆docId）
    private HashMap<String,ArrayList<Weight>> invertedIndex=new HashMap<>();

    // 新创建两个锁对象
    private Object locker1=new Object();
    private Object locker2=new Object();

    // 1.根据docId查正排
    public DocInfo getDocInfo(int docId) {
        return forwardIndex.get(docId);
    }

    // 2.根据分词结果查倒排
    public ArrayList<Weight> getInverted(String term) {
        return invertedIndex.get(term);
    }

    // 3.向索引中新增一条文档
    public void addDoc(String title,String url,String content) {
        // 新增文档操作，需要同时给正排索引和倒排索引新增信息
        // 构建正排索引
        DocInfo docInfo=buildForward(title,url,content);
        // 构建倒排索引
        buildInverted(docInfo);
    }

    private void buildInverted(DocInfo docInfo) {
        class WordCnt {
            // 表示这个词在标题中出现的次数
            public int titleCount;
            // 表示这个词在正文中出现的次数
            public int contentCount;
        }
        // 这个数据结构用来统计词频
        HashMap<String,WordCnt> wordCntHashMap=new HashMap<>();

        // 1.针对文档标题进行分词
        List<Term> terms= ToAnalysis.parse(docInfo.getTitle()).getTerms();
        // 2.遍历分词结果，统计每个词出现的次数
        for (Term term:terms) {
            // 先判定一下term是否存在
            String word=term.getName();
            WordCnt wordCnt=wordCntHashMap.get(word);
            if(wordCnt==null) {
                // 如果不存在,就创建一个新的键值对，插入进去,titleCount设为1
                WordCnt newWordCnt=new WordCnt();
                newWordCnt.titleCount=1;
                newWordCnt.contentCount=0;
                wordCntHashMap.put(word,newWordCnt);
            }else {
                // 如果存在,就找到之前的值，然后把对应的titleCount+1
                wordCnt.titleCount+=1;
            }
        }
        // 3.针对正文进行分词
        terms=ToAnalysis.parse(docInfo.getContent()).getTerms();
        // 4.遍历分词结果，统计每个词出现的次数
        for (Term term:terms) {
            // 先判定一下term是否存在
            String word=term.getName();
            WordCnt wordCnt=wordCntHashMap.get(word);
            if(wordCnt==null) {
                // 如果不存在,就创建一个新的键值对，插入进去,titleCount设为1
                WordCnt newWordCnt=new WordCnt();
                newWordCnt.titleCount=0;
                newWordCnt.contentCount=1;
                wordCntHashMap.put(word,newWordCnt);
            }else {
                // 如果存在,就找到之前的值，然后把对应的titleCount+1
                wordCnt.contentCount+=1;
            }
        }
        // 5.把上面的结果汇总到HashMap中
        /*
         6.遍历这个HashMap,依次来更新倒排索引中的结果
         最终文档的权重，设定成标题中出现的次数*10+正文中出现的次数
         */
        for (Map.Entry<String,WordCnt> entry: wordCntHashMap.entrySet()) {
            // 先根据这里的词，去倒排索引中查找一下
            synchronized (locker2) {
                List<Weight> invertedList=invertedIndex.get(entry.getKey());
                if(invertedList==null) {
                    // 如果为空，就插入一个新的键值对
                    ArrayList<Weight> newInvertedList=new ArrayList<>();
                    // 把新的文档构造成weight对象，插入进来
                    Weight weight=new Weight();
                    weight.setDocId(docInfo.getDocId());
                    weight.setWeight(entry.getValue().titleCount*10+entry.getValue().contentCount);
                    newInvertedList.add(weight);
                    invertedIndex.put(entry.getKey(),newInvertedList);
                }else {
                    // 如果非空，就把当前这个文档，构造出一个weight对象，插入到倒排拉链的后面
                    Weight weight=new Weight();
                    weight.setDocId(docInfo.getDocId());
                    weight.setWeight(entry.getValue().titleCount*10+entry.getValue().contentCount);
                    invertedList.add(weight);
                }
            }
        }
    }

    private DocInfo buildForward(String title, String url, String content) {
        DocInfo docInfo=new DocInfo();
        docInfo.setTitle(title);
        docInfo.setUrl(url);
        docInfo.setContent(content);
        synchronized (locker1) {
            docInfo.setDocId(forwardIndex.size());
            forwardIndex.add(docInfo);
        }
        return docInfo;
    }

    // 4.向数据库中保存索引
    public void save() {
        saveForwardIndex();
        saveInvertedIndex();
    }

    private void saveInvertedIndex() {
    }

    private void saveForwardIndex() {
        for (DocInfo docInfo:forwardIndex) {
            indexMapper.saveForwardIndex(docInfo.getDocId(),docInfo.getTitle(),docInfo.getUrl(),docInfo.getContent());
        }
    }

}
