import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Demo6 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream=new FileInputStream("./bbb.txt");
        while (true) {
            int b=inputStream.read();

        }
    }
}
