import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s1=sc.nextLine();
        String s2=sc.nextLine();
        System.out.print(match(s1,s2,0,0));
    }
    public static boolean match(String s1,String s2,int c1,int c2) {
        if(s1.length()==c1&&s2.length()==c2) {
            return true;
        }
        if(s1.length()==c1||s2.length()==c2) {
            return false;
        }
        if(s1.charAt(c1)=='?') {
            return match(s1,s2,c1+1,c2+1);
        }else if(s1.charAt(c1)=='*') {
            return match(s1,s2,c1+1,c2)||
                    match(s1,s2,c1+1,c2+1)||
                    match(s1,s2,c1,c2+1);
        }else if(s1.charAt(c1)==s2.charAt(c2)) {
            return match(s1,s2,c1+1,c2+1);
        }else {
            return false;
        }
    }
}