package com.example.demo.searcher;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
@Component
public class Parser {
    // 先指定一个加载文档的路径
    private static final String INPUT_PATH="D:\\GitHub\\api";
    // 创建一个index实例
    private Index index=new Index();

    private AtomicLong t1=new AtomicLong(0);
    private AtomicLong t2=new AtomicLong(0);

    // 该方法实现单线程制作索引
    public void run() {
        long beg=System.currentTimeMillis();
        System.out.println("索引制作开始!");
        // 1.根据上面指定的路径，枚举出该路径中所有的文件(html)，这个过程需要把所有子目录中的文件都能获取到
        ArrayList<File> fileList=new ArrayList<>();
        enumFile(INPUT_PATH,fileList);
        long endEnumFile=System.currentTimeMillis();
        System.out.println("枚举文件完毕！消耗时间："+(endEnumFile-beg)+"ms");

        // 2.针对上面罗列出的文件的路径，打开文件，读取文件内容，并进行解析、构建索引
        for(File f:fileList) {
            // 通过这个方法来解析单个的HTML文件
            System.out.println("开始解析："+f.getAbsolutePath());
            parseHTML(f);
        }
        long endFor=System.currentTimeMillis();
        System.out.println("遍历文件完毕！消耗时间："+(endFor-endEnumFile)+"ms");

        // 3.把在内存中构造好的索引数据结构，保存到数据库中
        index.save();
        long end=System.currentTimeMillis();
        System.out.println("索引制作完毕！消耗时间："+(end-beg)+"ms");
    }

    // 该方法实现多线程制作索引
    public void runByThread() throws InterruptedException {
        long beg=System.currentTimeMillis();
        System.out.println("索引制作开始");

        // 1.枚举出所有文件
        ArrayList<File> files=new ArrayList<>();
        enumFile(INPUT_PATH,files);
        // 2.循环遍历文件，此处为了能够通过多线程制作索引，就直接引入线程池
        CountDownLatch latch=new CountDownLatch(files.size());
        ExecutorService executorService= Executors.newFixedThreadPool(8);
        for (File f:files) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("解析："+f.getAbsolutePath());
                    parseHTML(f);
                    latch.countDown();
                }
            });
        }
        // await 方法会阻塞，直到所有的选手都调用countDown撞线之后，才能阻塞结束
        latch.await();
        // 手动的把线程池里的线程都干掉
        executorService.shutdown();
        // 3.把索引保存到数据库
        index.save();

        long end=System.currentTimeMillis();
        System.out.println("索引制作完毕！消耗时间："+(end-beg)+"ms");
        System.out.println("t1:"+t1+",t2:"+t2);
    }
    private void parseHTML(File f) {
        // 1.解析出HTML的标题
        String title=parseTitle(f);
        // 2.解析出HTML对应的URL
        String url=parseUrl(f);
        // 3.解析出HTML对应的正文
        long beg=System.nanoTime();
        String content=parseContent(f);
        long mid=System.nanoTime();
        // 4.把解析出来的信息，加入到索引中
        index.addDoc(title,url,content);
        long end=System.nanoTime();

        // 频繁打印会拖慢速度，所以不打印，只累加
        t1.addAndGet(mid-beg);
        t2.addAndGet(end-mid);
    }

    private String parseContent(File f) {
        // 按照一个字符一个字符的方式来读取，以<和>来控制拷贝数据的开关
        try (BufferedReader bufferedReader=new BufferedReader(new FileReader(f),1024*1024)){
            // 是否要进行拷贝的开关
            boolean isCopy=true;
            // 用于保存结果的StringBuilder
            StringBuilder content=new StringBuilder();
            while (true) {
                /*
                此处的read，返回值为int,而不是char
                如果读到文件末尾，继续读就会返回-1
                 */
                int ret=bufferedReader.read();
                if(ret==-1) {
                    // 表示文件读完了
                    break;
                }
                // 如果结果不是-1，那么就是一个合法的字符
                char c=(char) ret;
                if(isCopy) {
                    // 开关打开的状态，遇到普通字符就应该拷贝到StringBuilder中
                    if(c=='<') {
                        // 关闭开关
                        isCopy=false;
                        continue;
                    }
                    if(c=='\n'||c=='\r') {
                        // 为了去掉换行，把换行替换成空格
                        c=' ';
                    }
                    // 其他字符，直接进行拷贝即可，把结果给拷贝到最终的StringBuilder中
                    content.append(c);
                } else {
                    // 开关关闭的状态，就暂时不拷贝，直到遇到>
                    if(c=='>') {
                        isCopy=true;
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

    private String parseContentByRegex(File f) {
        // 1.先把整个文件内容都读取出来
        String content=readFile(f);
        // 2.使用正则替换掉< script >标签
        content=content.replaceAll("<script.*?>(.*?)</script>"," ");
        // 3.使用正则替换掉其他标签
        content=content.replaceAll("<.*?>"," ");
        // 4.多个空格合并成一个
        content=content.replaceAll("\\s+"," ");
        return content;
    }
    private String parseUrl(File f) {
        String part1="https://docs.oracle.com/javase/8/docs/api/";
        String part2=f.getAbsolutePath().substring(INPUT_PATH.length());
        return part1+part2;
    }

    private String parseTitle(File f) {
        String name= f.getName();
        return name.substring(0,name.length()-".html".length());
    }

    /**
     * 枚举文件
     * @param inputPath 表示从哪个目录开始进行递归遍历
     * @param fileList  表示递归得到的结果
     */
    private void enumFile(String inputPath, ArrayList<File> fileList) {
        File rootPath=new File(inputPath);
        // 使用listFiles只能看到一级目录，所以需要递归获取所有目录中的内容
        File[] files=rootPath.listFiles();
        for(File f:files) {
            // 根据当前f的类型，来决定是否要递归
            // 如果f是一个普通文件，就把f加入到fileList结果中
            // 如果f是一个目录，就递归的调用enumFile方法，来进一步获取子目录中的内容
            if(f.isDirectory()) {
                enumFile(f.getAbsolutePath(),fileList);
            }else {
                if(f.getAbsolutePath().endsWith(".html")) {
                    fileList.add(f);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 通过main方法来实现整个制作索引的过程
        Parser parser=new Parser();
        parser.runByThread();
    }
}
