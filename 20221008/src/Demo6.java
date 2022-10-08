import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Demo6 {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要扫描的路径：");
        File rootDir=new File(scanner.next());
        if(!rootDir.isDirectory()) {
            System.out.println("您输入的目录不存在！");
            return;
        }
        System.out.println("请输入要搜索的关键词：");
        String toDelete=scanner.next();

        scanDir(rootDir,toDelete);
    }

    private static void scanDir(File rootDir, String toDelete) throws IOException {
        System.out.println("当前访问:"+rootDir.getCanonicalPath());
        File[] files=rootDir.listFiles();
        if(files==null) {
            return;
        }
        for(File f:files) {
            if(f.isDirectory()) {
                scanDir(f,toDelete);
            } else {
                checkDelete(f,toDelete);
            }
        }
    }

    private static void checkDelete(File f, String toDelete) throws IOException {
        if(f.getName().contains(toDelete)) {
            System.out.println("该单词："+toDelete+"被"+f.getCanonicalPath()+"包含了，是否要删除？(Y/N)");
            Scanner scanner=new Scanner(System.in);
            String choice=scanner.next();
            if(choice.equals("Y")||choice.equals("y")) {
                f.delete();
            }
        }
    }
}
