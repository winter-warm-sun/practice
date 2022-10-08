import java.io.*;
import java.util.Scanner;

public class Demo7 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入源文件：");
        File srcFile=new File(scanner.next());
        System.out.println("请输入目标文件：");
        File destFile=new File(scanner.next());
        if(!srcFile.isFile()) {
            System.out.println("输入的源文件有误！");
            return;
        }
        if(!destFile.getParentFile().isDirectory()) {
            System.out.println("输入的目标文件有误！");
            return;
        }
        try (InputStream inputStream=new FileInputStream(srcFile);
             OutputStream outputStream=new FileOutputStream(destFile)){
            while (true) {
                int ret=inputStream.read();
                if(ret==-1) {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
