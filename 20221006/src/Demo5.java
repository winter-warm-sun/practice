import java.awt.*;
import java.io.*;

public class Demo5 {
    public static void main(String[] args) throws IOException {
        try(OutputStream outputStream=new FileOutputStream("./bbb.txt")) {
            PrintWriter writer=new PrintWriter(outputStream);

            writer.println(1);
            writer.printf("a =%d\n",10);
        }
    }
}
