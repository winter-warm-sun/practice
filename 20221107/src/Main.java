import java.util.*;
public class Main {
    public static void main(String []args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        for(int i=0;i<s.length();i++) {
            String a= String.valueOf(s.charAt(i));
            if(s.indexOf(a)==s.lastIndexOf(a)) {
                System.out.println(a);
                return;
            }
        }
        System.out.println(-1);
    }
}