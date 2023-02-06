package com.example.demo.searcher;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// 通过这个类, 来完成整个的搜索过程
public class DocSearcher {
    // 停用词文件的路径
    private static String STOP_WORD_PATH = "D:\\project\\doc_searcher_index\\stop_word.txt";

    // 使用这个 HashSet 来保存停用词
    private HashSet<String> stopWords = new HashSet<>();

    // 此处要加上索引对象的实例
    // 同时要完成索引加载的工作.
    private Index index = new Index();

    public DocSearcher() {
        index.load();
        loadStopWords();
    }

    // 完成整个搜索过程的方法.
    // 参数(输入部分) 就是用户给出的查询词
    // 返回值(输出部分) 就是搜索结果的集合
    public List<Result> search(String query) {
        // 1. [分词] 针对 query 这个查询词进行分词
        List<Term> oldTerms = ToAnalysis.parse(query).getTerms();
        List<Term> terms = new ArrayList<>();
        // 针对分词结果, 使用暂停词表进行过滤
        for (Term term : oldTerms) {
            if (stopWords.contains(term.getName())) {
                continue;
            }
            terms.add(term);
        }

        // 2. [触发] 针对分词结果来查倒排
        List<List<Weight>> termResult = new ArrayList<>();
        for (Term term : terms) {
            String word = term.getName();
            // 虽然倒排索引中, 有很多的词. 但是这里的词一定都是之前的文档中存在的.
            List<Weight> invertedList = index.getInverted(word);
            if (invertedList == null) {
                // 说明这个词在所有文档中都不存在.
                continue;
            }
            termResult.add(invertedList);
        }
        // 3. [合并] 针对多个分词结果触发出的相同文档, 进行权重合并
        List<Weight> allTermResult = mergeResult(termResult);
        // 4. [排序] 针对触发的结果按照权重降序排序
        allTermResult.sort(new Comparator<Weight>() {
            @Override
            public int compare(Weight o1, Weight o2) {
                // 如果是升序排序: return o1.getWeight() - o2.getWeight();
                // 如果是降序排序: return o2.getWeight() - o1.getWeight();
                return o2.getWeight() - o1.getWeight();
            }
        });
        // 5. [包装结果] 针对排序的结果, 去查正排, 构造出要返回的数据.
        List<Result> results = new ArrayList<>();
        for (Weight weight : allTermResult) {
            DocInfo docInfo = index.getDocInfo(weight.getDocId());
            Result result = new Result();
            result.setTitle(docInfo.getTitle());
            result.setUrl(docInfo.getUrl());
            result.setDesc(GenDesc(docInfo.getContent(), terms));
            results.add(result);
        }
        return results;
    }

    // 通过这个内部类, 来描述一个元素在二维数组中的位置
    static class Pos {
        public int row;
        public int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private List<Weight> mergeResult(List<List<Weight>> source) {
        // 在进行合并的时候, 是把多个行合并成一行了.
        // 合并过程中势必是需要操作这个二维数组(二维List) 里面的每个元素的....
        // 操作元素就涉及到 "行" "列" 这样的概念~~ 要想确定二维数组中的一个元素, 就需要明确知道 行 和 列

        // 1. 先针对每一行进行排序(按照 id 进行升序排序)
        for (List<Weight> curRow : source) {
            curRow.sort(new Comparator<Weight>() {
                @Override
                public int compare(Weight o1, Weight o2) {
                    return o1.getDocId() - o2.getDocId();
                }
            });
        }
        // 2. 借助一个优先队列, 针对这些行进行合并
        //    target 表示合并的结果
        List<Weight> target = new ArrayList<>();
        //  2.1 创建优先级队列, 并指定比较规则(按照 Weight 的 docId, 取小的更优先)
        PriorityQueue<Pos> queue = new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                // 先根据 pos 值找到对应的 Weight 对象, 再根据 Weight 的 docId 来排序
                Weight w1 = source.get(o1.row).get(o1.col);
                Weight w2 = source.get(o2.row).get(o2.col);
                return w1.getDocId() - w2.getDocId();
            }
        });
        //  2.2 初始化队列, 把每一行的第一个元素放到队列中.
        for (int row = 0; row < source.size(); row++) {
            // 初始插入的元素的 col 就是 0
            queue.offer(new Pos(row, 0));
        }
        //  2.3 循环的取队首元素(也就是当前这若干行中最小的元素)
        while (!queue.isEmpty()) {
            Pos minPos = queue.poll();
            Weight curWeight = source.get(minPos.row).get(minPos.col);
            //  2.4 看看这个取到的 Weight 是否和前一个插入到 target 中的结果是相同的 docId
            //      如果是, 就合并
            if (target.size() > 0) {
                // 取出了上次插入的元素
                Weight lastWeight = target.get(target.size() - 1);
                if (lastWeight.getDocId() == curWeight.getDocId()) {
                    // 说明遇到了相同的文档.
                    // 合并权重
                    lastWeight.setWeight(lastWeight.getWeight() + curWeight.getWeight());
                } else {
                    // 如果文档 id 不相同, 就直接把 curWeight 给插入到 target 的末尾
                    target.add(curWeight);
                }
            } else {
                // 如果 target 当前是空着的, 就直接插入即可
                target.add(curWeight);
            }
            //  2.5 把当前元素处理完了之后, 要把对应这个元素的光标往后移动, 去取这一行的下一个元素
            Pos newPos = new Pos(minPos.row, minPos.col + 1);
            if (newPos.col >= source.get(newPos.row).size()) {
                // 如果移动光标之后, 超出了这一行的列数, 就说明到达末尾了.
                // 到达末尾之后说明这一行就处理完毕了~~
                continue;
            }
            queue.offer(newPos);
        }
        return target;
    }

    private String GenDesc(String content, List<Term> terms) {
        // 先遍历分词结果, 看看哪个结果是在 content 中存在.
        int firstPos = -1;
        for (Term term : terms) {
            // 别忘了, 分词库直接针对词进行转小写了.
            // 正因为如此, 就必须把正文也先转成小写, 然后再查询
            String word = term.getName();
            // 此处需要的是 "全字匹配", 让 word 能够独立成词, 才要查找出来, 而不是只作为词的一部分.
            // 此处的全字匹配的实现并不算特别严谨. 更严谨的做法, 可以使用正则表达式.
            content = content.toLowerCase().replaceAll("\\b" + word + "\\b", " " + word + " ");
            firstPos = content.indexOf(" " + word + " ");
            if (firstPos >= 0) {
                // 找到了位置
                break;
            }
        }
        // 后面还需要用到这个 firstPos
        if (firstPos == -1) {
            // 所有的分词结果都不在正文中存在.
            // 因此这是属于比较极端的情况~
            // 此时就直接返回一个空的描述了.
            // 或者也可以直接取正文的前 160 个字符作为描述.
            if (content.length() > 160) {
                return content.substring(0, 160) + "...";
            }
            return content;
        }
        // 从 firstPos 作为基准位置, 往前找 60 个字符, 作为描述的起始位置.
        String desc = "";
        int descBeg = firstPos < 60 ? 0 : firstPos - 60;
        if (descBeg + 160 > content.length()) {
            desc = content.substring(descBeg);
        } else {
            desc = content.substring(descBeg, descBeg + 160) + "...";
        }

        // 在此处加上一个替换操作. 把描述中的和分词结果相同的部分, 给加上一层 <i> 标签. 就可以通过 replace 的方式来实现.
        for (Term term : terms) {
            String word = term.getName();
            // 注意, 此处要进行全字匹配. 也就是当查询词为 List 的时候 不能把 ArrayList 中的 List 给单独标红
            desc = desc.replaceAll("(?i) " + word + " ", "<i> " + word + " </i>");
        }
        return desc;
    }

    public void loadStopWords() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(STOP_WORD_PATH))) {
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    // 读取文件完毕!
                    break;
                }
                stopWords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DocSearcher docSearcher = new DocSearcher();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("-> ");
            String query = scanner.next();
            List<Result> results = docSearcher.search(query);
            for (Result result : results) {
                System.out.println("================================");
                System.out.println(result);
            }
        }
    }
}
