package com.example.demo.searcher;

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
    private static final String INPUT_PATH = "D:\\project\\doc_searcher_index\\docs\\api";
    // 创建一个 searcher.Index 实例
    private Index index = new Index();

    private AtomicLong t1 = new AtomicLong(0);
    private AtomicLong t2 = new AtomicLong(0);

    // 通过这个方法实现单线程制作索引
    public void run() {
        long beg = System.currentTimeMillis();
        System.out.println("索引制作开始!");
        // 整个 searcher.Parser 类的入口
        // 1. 根据上面指定的路径, 枚举出该路径中所有的文件(html). 这个过程需要把所有子目录中的文件都能获取到
        ArrayList<File> fileList = new ArrayList<>();
        enumFile(INPUT_PATH, fileList);
        long endEnumFile = System.currentTimeMillis();
        System.out.println("枚举文件完毕! 消耗时间: " + (endEnumFile - beg) + "ms");
//        System.out.println(fileList);
//        System.out.println(fileList.size());
        // 2. 针对上面罗列出的文件的路径, 打开文件, 读取文件内容, 并进行解析, 并构建索引.
        for (File f : fileList) {
            // 通过这个方法来解析单个的 HTML 文件
            System.out.println("开始解析: " + f.getAbsolutePath());
            parseHTML(f);
        }
        long endFor = System.currentTimeMillis();
        System.out.println("遍历文件完毕! 消耗时间: " + (endFor - endEnumFile) + "ms");
        // 3. 把在内存中构造好的索引数据结构, 保存到指定的文件中.
        index.save();
        long end = System.currentTimeMillis();
        System.out.println("索引制作完毕! 消耗时间: " + (end - beg) + "ms");
    }

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

    public String parseContent(File f) {
        // 先按照一个字符一个字符的方式来读取. 以 < 和 > 来控制拷贝数据的开关.
        // 手动把缓冲区设置成 1M 大小~
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(f), 1024 * 1024)) {
            // 加上一个是否要进行拷贝, 开关.
            boolean isCopy = true;
            // 还得准备一个保存结果的 StringBuilder
            StringBuilder content = new StringBuilder();
            while (true) {
                // 注意, 此处的 read, 返回值是一个 int, 而不是 char!
                // 按理说, 应该是一次读一个字符, 就返回 char 就够了呀?
                // 此处使用 int 作为返回值, 主要是为了表示一些非法情况!
                // 如果读到了文件末尾, 继续读, 就会返回 -1
                int ret = bufferedReader.read();
                if (ret == -1) {
                    // 表示文件读完了
                    break;
                }
                // 如果这个结果不是 -1, 那么就是一个合法的字符了.
                char c = (char)ret;
                if (isCopy) {
                    // 开关打开的状态, 遇到普通字符就应该拷贝到 StringBuilder 中
                    if (c == '<') {
                        // 关闭开关
                        isCopy = false;
                        continue;
                    }
                    if (c == '\n' || c == '\r') {
                        // 为了去掉换行, 把换行替换成空格了.
                        c = ' ';
                    }
                    // 其他字符, 直接进行拷贝即可, 把结果给拷贝到最终的 StringBuilder 中
                    content.append(c);
                } else {
                    // 开关关闭的状态, 就暂时不拷贝, 直到遇到 >
                    if (c == '>') {
                        isCopy = true;
                    }
                }
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String readFile(File f) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(f))) {
            StringBuilder content = new StringBuilder();
            while (true) {
                int ret = bufferedReader.read();
                if (ret == -1) {
                    break;
                }
                char c = (char)ret;
                if (c == '\n' || c == '\r') {
                    c = ' ';
                }
                content.append(c);
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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
        String part1 = "https://docs.oracle.com/javase/8/docs/api/";
        String part2 = f.getAbsolutePath().substring(INPUT_PATH.length());
        return part1 + part2;
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
