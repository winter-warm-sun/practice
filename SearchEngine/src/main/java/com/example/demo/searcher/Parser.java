package com.example.demo.searcher;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.*;
import java.util.ArrayList;

public class Parser {
    // 先指定一个加载文档的路径
    private static final String INPUT_PATH="D:\\GitHub\\api";

    //
    public void run() {
        // 1.根据上面指定的路径，枚举出该路径中所有的文件(html)，这个过程需要把所有子目录中的文件都能获取到
        ArrayList<File> fileList=new ArrayList<>();
        enumFile(INPUT_PATH,fileList);

        // 2.针对上面罗列出的文件的路径，打开文件，读取文件内容，并进行解析、构建索引
        for(File f:fileList) {
            // 通过这个方法来解析单个的HTML文件
            System.out.println("开始解析："+f.getAbsolutePath());
            parseHTML(f);
        }
    }

    private void parseHTML(File f) {
        // 1.解析出HTML的标题
        String title=parseTitle(f);
        // 2.解析出HTML对应的URL
        String url=parseUrl(f);
        // 3.解析出HTML对应的正文
        String content=parseContent(f);

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

    public static void main(String[] args) {
        // 通过main方法来实现整个制作索引的过程
        Parser parser=new Parser();
        parser.run();
    }
}
