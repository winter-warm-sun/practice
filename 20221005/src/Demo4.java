import java.io.File;

public class Demo4 {
    public static void main(String[] args) {
        File srcFile=new File("./aaa.txt");
        File destFile=new File("./bbb.txt");
        srcFile.renameTo(destFile);
    }
}
