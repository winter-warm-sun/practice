import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// 通过这个类在内存中来构造出索引结构
public class Index {
    private static String INDEX_PATH = "D:/project/doc_searcher_index/";


    private ObjectMapper objectMapper = new ObjectMapper();

    // 使用数组下标表示 docId
    private ArrayList<DocInfo> forwardIndex = new ArrayList<>();

    // 使用 哈希表 来表示倒排索引
    // key 就是词
    // value 就是一组和这个词关联的文章
    private HashMap<String, ArrayList<Weight>> invertedIndex = new HashMap<>();

    // 新创建两个锁对象
    private Object locker1 = new Object();
    private Object locker2 = new Object();

    // 这个类要提供的方法:
    // 1. 给定一个 docId, 在正排索引中, 查询文档的详细信息.
    public DocInfo getDocInfo(int docId) {
        return forwardIndex.get(docId);
    }

    // 2. 给定一个词, 在倒排索引中, 查哪些文档和这个词关联.
    //    仔细思考这里的返回值. 单纯的返回一个 整数 的 List 是否可行呢? 这样不太好
    //    词和文档之间是存在一定的 "相关性" 的.
    public ArrayList<Weight> getInverted(String term) {
        return invertedIndex.get(term);
    }

    // 3. 往索引中新增一个文档.
    public void addDoc(String title, String url, String content) {
        // 新增文档操作, 需要同时给正排索引和倒排索引新增信息
        // 构建正排索引
        DocInfo docInfo = buildForward(title, url, content);
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
        HashMap<String, WordCnt> wordCntHashMap = new HashMap<>();

        // 1. 针对文档标题进行分词.
        List<Term> terms = ToAnalysis.parse(docInfo.getTitle()).getTerms();
        // 2. 遍历分词结果, 统计每个词出现的次数.
        for (Term term : terms) {
            // 先判定一下 term 是否存在.
            String word = term.getName();
            WordCnt wordCnt = wordCntHashMap.get(word);
            if (wordCnt == null) {
                // 如果不存在, 就创建一个新的键值对, 插入进去. titleCount 设为 1
                WordCnt newWordCnt = new WordCnt();
                newWordCnt.titleCount = 1;
                newWordCnt.contentCount = 0;
                wordCntHashMap.put(word, newWordCnt);
            } else {
                // 如果存在, 就找到之前的值, 然后把对应的 titleCount + 1
                wordCnt.titleCount += 1;
            }
        }
        // 3. 针对正文页进行分词.
        terms = ToAnalysis.parse(docInfo.getContent()).getTerms();
        // 4. 遍历分词结果, 统计每个词出现的次数.
        for (Term term : terms) {
            String word = term.getName();
            WordCnt wordCnt = wordCntHashMap.get(word);
            if (wordCnt == null) {
                WordCnt newWordCnt = new WordCnt();
                newWordCnt.titleCount = 0;
                newWordCnt.contentCount = 1;
                wordCntHashMap.put(word, newWordCnt);
            } else {
                wordCnt.contentCount += 1;
            }
        }
        // 5. 把上面的结果汇总到一个 HashMap 里面.
        //    最终文档的权重, 就设定成标题中出现的次数 * 10 + 正文中出现的次数.
        // 6. 遍历刚才这个 HashMap, 依次来更新倒排索引中的结构了.
        for (Map.Entry<String, WordCnt> entry : wordCntHashMap.entrySet()) {
            // 先根据这里的词, 去倒排索引中查一查~
            // 倒排拉链
            synchronized (locker2) {
                List<Weight> invertedList = invertedIndex.get(entry.getKey());
                if (invertedList == null) {
                    // 如果为空, 就插入一个新的键值对
                    ArrayList<Weight> newInvertedList = new ArrayList<>();
                    // 把新的文档(当前 searcher.DocInfo), 构造成 searcher.Weight 对象, 插入进来.
                    Weight weight = new Weight();
                    weight.setDocId(docInfo.getDocid());
                    // 权重计算公式: 标题中出现的次数 * 10 + 正文中出现的次数
                    weight.setWeight(entry.getValue().titleCount * 10 + entry.getValue().contentCount);
                    newInvertedList.add(weight);
                    invertedIndex.put(entry.getKey(), newInvertedList);
                } else {
                    // 如果非空, 就把当前这个文档, 构造出一个 searcher.Weight 对象, 插入到倒排拉链的后面
                    Weight weight = new Weight();
                    weight.setDocId(docInfo.getDocid());
                    // 权重计算公式: 标题中出现的次数 * 10 + 正文中出现的次数
                    weight.setWeight(entry.getValue().titleCount * 10 + entry.getValue().contentCount);
                    invertedList.add(weight);
                }
            }
        }
    }

    private DocInfo buildForward(String title, String url, String content) {
        DocInfo docInfo = new DocInfo();
        docInfo.setTitle(title);
        docInfo.setUrl(url);
        docInfo.setContent(content);
        synchronized (locker1) {
            docInfo.setDocid(forwardIndex.size());
            forwardIndex.add(docInfo);
        }
        return docInfo;
    }

    // 4. 把内存中的索引结构保存到磁盘中.
    public void save() {
        // 使用两个文件, 分别保存正排和倒排
        long beg = System.currentTimeMillis();
        System.out.println("保存索引开始!");
        // 1. 先判定一下索引对应的目录是否存在, 不存在就创建.
        File indexPathFile = new File(INDEX_PATH);
        if (!indexPathFile.exists()) {
            indexPathFile.mkdirs();
        }
        File forwardIndexFile = new File(INDEX_PATH + "forward.txt");
        File invertedIndexFile = new File(INDEX_PATH + "inverted.txt");
        try {
            objectMapper.writeValue(forwardIndexFile, forwardIndex);
            objectMapper.writeValue(invertedIndexFile, invertedIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("保存索引完成! 消耗时间: " + (end - beg) + " ms");
    }

    // 5. 把磁盘中的索引数据加载到内存中.
    public void load() {
        long beg = System.currentTimeMillis();
        System.out.println("加载索引开始!");
        // 1. 先设置一下加载索引的路径
        File forwardIndexFile = new File(INDEX_PATH + "forward.txt");
        File invertedIndexFile = new File(INDEX_PATH + "inverted.txt");
        try {
            forwardIndex = objectMapper.readValue(forwardIndexFile, new TypeReference<ArrayList<DocInfo>>() {});
            invertedIndex = objectMapper.readValue(invertedIndexFile, new TypeReference<HashMap<String, ArrayList<Weight>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("加载索引结束! 消耗时间: " + (end - beg) + " ms");
    }

    public static void main(String[] args) {
        Index index = new Index();
        index.load();
        System.out.println("索引加载完成");
    }
}