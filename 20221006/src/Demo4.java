import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Demo4 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream=new FileInputStream("./bbb.txt");

        Scanner scanner=new Scanner(inputStream);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }

        inputStream.close();
    }
}
