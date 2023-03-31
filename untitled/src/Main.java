import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s1="100.20.003";
        String s2="102.21.003";
        String []ss1=new String[3];
        ss1[0]="100";
        ss1[1]="20";
        ss1[2]="003";
        String []ss2=new String[3];
        ss2[0]="102";
        ss2[1]="21";
        ss2[2]="003";
        for(int i=0;i<ss1.length;i++) {
            if(ss1[i].compareTo(ss2[i])>0) {
                System.out.println(s1);
                break;
            }else {
                System.out.println(s2);
                break;
            }
        }
    }
}
