import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


public class Parser {
    // 先指定一个加载文档的路径
    private static final String INPUT_PATH = "D:/project/doc_searcher_index/docs/api/";
    // 创建一个 searcher.Index 实例
    private Index index = new Index();

    private AtomicLong t1 = new AtomicLong(0);
    private AtomicLong t2 = new AtomicLong(0);

    // 通过这个方法实现 "多线程制作索引"
    public void runByThread() throws InterruptedException {
        long beg = System.currentTimeMillis();
        System.out.println("索引制作开始!");

        // 1. 枚举出所有的文件
        ArrayList<File> files = new ArrayList<>();
        enumFile(INPUT_PATH, files);
        // 2. 循环遍历文件. 此处为了能够通过多线程制作索引, 就直接引入线程池.
        CountDownLatch latch = new CountDownLatch(files.size());
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (File f : files) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("解析 " + f.getAbsolutePath());
                    parseHTML(f);
                    latch.countDown();
                }
            });
        }
        // await 方法会阻塞, 直到所有的选手都调用 countDown 撞线之后, 才能阻塞结束.
        latch.await();
        // 手动的把线程池里面的线程都干掉!
        executorService.shutdown();
        // 3. 保存索引
        index.save();

        long end = System.currentTimeMillis();
        System.out.println("索引制作完毕! 消耗时间: " + (end - beg) + "ms");
        System.out.println("t1: " + t1 + ", t2: " + t2);
    }

    private void parseHTML(File f) {
        // 1. 解析出 HTML 的标题
        String title = parseTitle(f);
        // 2. 解析出 HTML 对应的 URL
        String url = parseUrl(f);
        // 3. 解析出 HTML 对应的正文 (有了正文才有后续的描述)
        long beg = System.nanoTime();
        // String content = parseContent(f);
        String content = parseContentByRegex(f);
        long mid = System.nanoTime();
        // 4. 把解析出来的这些信息, 加入到索引当中
        index.addDoc(title, url, content);
        long end = System.nanoTime();
        // 注意!! 由于 parseHTML 会被循环调用很多次, 单次调用其实时间较短.
        // 加入频繁的打印反而可能会拖慢这个速度本身~~
        t1.addAndGet(mid - beg);
        t2.addAndGet(end - mid);
    }

    private String readFile(File f) {
        StringBuilder content=new StringBuilder();
        try (BufferedReader bufferedReader=new BufferedReader(new FileReader(f))){
            while (true) {
                String line=bufferedReader.readLine();
                if(line==null) {
                    break;
                }
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // 这个方法内部就基于正则表达式, 实现去标签, 以及去除 script
    public String parseContentByRegex(File f) {
        // 1. 先把整个文件都读到 String 里面
        String content = readFile(f);
        // 2. 替换掉 script 标签
        content = content.replaceAll("<script.*?>(.*?)</script>", " ");
        // 3. 替换掉普通的 html 标签
        content = content.replaceAll("<.*?>", " ");
        // 4. 使用正则把多个空格, 合并成一个空格
        content = content.replaceAll("\\s+", " ");
        return content;
    }

    private String parseUrl(File f) {
        String part1="https://docs.oracle.com/javase/8/docs/api";
        String part2=f.getAbsolutePath().substring(INPUT_PATH.length());
        String url=part1+part2;
        url=url.replace("\\","/");
        return url;
    }

    private String parseTitle(File f) {
        String name = f.getName();
        return name.substring(0, name.length() - ".html".length());
    }

    // 第一个参数表示从哪个目录开始进行递归遍历.
    // 第二个参数表示递归得到的结果.
    private void enumFile(String inputPath, ArrayList<File> fileList) {
        File rootPath = new File(inputPath);
        // listFiles 能够获取到 rootPath 当前目录下所包含的文件/目录
        // 使用 listFiles 只能看到一级目录, 看不到子目录里的内容.
        // 要想看到子目录中的内容, 还需要进行递归.
        File[] files = rootPath.listFiles();
        for (File f : files) {
            // 根据当前 f 的类型, 来决定是否要递归.
            // 如果 f 是一个普通文件, 就把 f 加入到 fileList 结果中
            // 如果 f 是一个目录, 就递归的调用 enumFile 这个方法, 来进一步的获取子目录中的内容.
            if (f.isDirectory()) {
                enumFile(f.getAbsolutePath(), fileList);
            } else {
                if (f.getAbsolutePath().endsWith(".html")) {
                    fileList.add(f);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 通过 main 方法来实现整个制作索引的 过程
        Parser parser = new Parser();
        // parser.run();
        parser.runByThread();
    }
}
