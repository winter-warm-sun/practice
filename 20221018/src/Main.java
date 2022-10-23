import java.util.*;

public class Main {
    public static int binInsert(int n, int m, int j, int i) {
        StringBuffer sb1=new StringBuffer();
        while(n!=0) {
            sb1.append(n%2);
            n/=2;
        }
        StringBuffer sb2=new StringBuffer();
        while(m!=0) {
            sb2.append(m%2);
            m/=2;
        }
        sb1=sb1.replace(j,sb2.length()+j,sb2.toString());
        String s=sb1.toString();
        int end=0;
        for(int i1=0;i1<s.length();i1++) {
            end=end+(s.charAt(i1)-'0')*(int)Math.pow(2,i1);
        }
        return end;
    }

    public static void main(String[] args) {
        System.out.println(binInsert(1024, 19, 2, 6));
    }
}