import com.sun.deploy.util.SyncAccess;

import java.util.HashSet;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        HashSet<String> set=new HashSet<>();
        while (scanner.hasNextLine()) {
            String s=scanner.nextLine();
            String[]ss=s.split(" ");
            for(String x:ss) {
                set.add(x);
            }
        }
        System.out.println(set.size());
    }
}
