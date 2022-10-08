import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Demo {
    public static void main(String[] args) throws IOException {
        OutputStream outputStream=new FileOutputStream("./bbb.txt");
        outputStream.write(97);
        outputStream.write(98);
        outputStream.write(99);

        outputStream.close();
    }
}
