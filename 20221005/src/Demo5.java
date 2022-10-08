import java.io.File;
import java.util.Arrays;

public class Demo5 {
    public static void main(String[] args) {
        File f=new File("./testDir");
        String[] results=f.list();
        System.out.println(Arrays.toString(results));
    }
}
