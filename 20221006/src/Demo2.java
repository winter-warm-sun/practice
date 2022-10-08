import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        Reader reader=new FileReader("./bbb.txt");
        while (true) {
            int ret=reader.read();
            if(ret==-1) {
                break;
            }
            char ch=(char)ret;
            System.out.println(ch);
        }
        reader.close();
    }
}
