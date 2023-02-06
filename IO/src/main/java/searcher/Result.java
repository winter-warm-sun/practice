package searcher;

// 这个类来表示一个搜索结果
public class Result {
    private String title;
    private String url;
    // 描述是正文的一段摘要.
    private String desc;

    @Override
    public String toString() {
        return "searcher.Result{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
