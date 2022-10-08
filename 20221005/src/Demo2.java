import java.io.File;
import java.io.IOException;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        File f=new File("./aaa.txt");
        //判定文件是否存在
        System.out.println(f.exists());
        //判定是否是目录
        System.out.println(f.isDirectory());
        //判定是否是文件
        System.out.println(f.isFile());

        //把这个文件创建出来
        f.createNewFile();

        //判定文件存在
        System.out.println(f.exists());
        //判定是否是目录
        System.out.println(f.isDirectory());
        //判定是否是文件
        System.out.println(f.isFile());
    }
}
