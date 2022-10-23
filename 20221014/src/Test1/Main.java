package Test1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String A=scanner.next();
        String B=scanner.next();
        int count=0;
        for (int i = 0; i <=A.length() ; i++) {
            StringBuilder sb=new StringBuilder(A);
            sb.insert(i,B);
            if(isTrue(sb)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isTrue(StringBuilder sb) {
        String s=sb.toString();
        String ss=sb.reverse().toString();
        if(s.equals(ss)) {
            return true;
        }
        return false;
    }
}
