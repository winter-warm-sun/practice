import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String[] ss=new String[3];
        ss[0]="abcde";
        ss[1]="cde";
        ss[2]="bde";
        for(int i=1;i<ss[0].length();i++) {
            String s=ss[0].substring(ss[0].length()-i);
            if(!contain(ss,s)) {
                System.out.println(ss[0].substring(ss[0].length()-i+1));
                break;
            }
        }
    }

    public static boolean contain(String[] ss,String s) {
        boolean flag=true;
        for(int i=1;i<ss.length;i++) {
            if(!ss[i].endsWith(s)) {
                flag=false;
                break;
            }
        }
        return flag;
    }
}
