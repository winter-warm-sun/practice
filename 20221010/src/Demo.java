import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();

        String ss=scanner.nextLine();
        for(int i=0;i<ss.length();i++) {
            s=s.replaceAll(String.valueOf(ss.charAt(i)),"");
        }
        System.out.println(s);
    }
}
