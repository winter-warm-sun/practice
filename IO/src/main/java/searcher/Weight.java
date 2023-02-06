package searcher;

// 这个类就是把 文档id 和 文档与词的相关性 权重 进行一个包裹.
public class Weight {
    private int docId;
    // 这个 weight 就表示 文档 和 词 之间的 "相关性"
    // 这个值越大, 就认为相关性越强. weight 这个值具体咋算, 后面再说
    private int weight;

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
