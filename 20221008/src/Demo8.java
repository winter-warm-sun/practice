import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Demo8 {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要扫描的路径：");
        File rootDir=new File(scanner.next());
        System.out.println("请输入要查的词：");
        String toFind=scanner.next();

        scanDir(rootDir,toFind);
    }

    private static void scanDir(File rootDir, String toFind) throws IOException {
        File[] files=rootDir.listFiles();
        if(files==null) {
            return;
        }
        for (File f:files) {
            if(f.isDirectory()) {
                scanDir(f,toFind);
            } else {
                checkFile(f,toFind);
            }
        }
    }

    private static void checkFile(File f, String toFind) throws IOException {
        if(f.getName().contains(toFind)) {
            System.out.println(f.getCanonicalPath()+"文件名中包含"+toFind);
        }
        try (InputStream inputStream=new FileInputStream(f)){
            StringBuilder sb=new StringBuilder();
            Scanner scanner=new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                sb.append((scanner.nextLine()+"\n"));
            }
            if(sb.indexOf(toFind)>-1) {
                System.out.println(f.getCanonicalPath()+"文件内容包含"+toFind);
            }
        }
    }
}
