import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Demo3 {
    public static void main(String[] args) throws IOException {
        Writer writer=new FileWriter("./bbb.txt");
        writer.write("hello world");
        writer.close();
    }
}
