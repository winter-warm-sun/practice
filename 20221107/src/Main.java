import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String cmd=sc.nextLine();
        int cur=1;
        int first=1;
        if(n<=4) {
            for(int i=0;i<cmd.length();i++) {
                if(cmd.charAt(i)=='U') {
                    cur--;
                    if(cur==0) {
                        cur=n;
                    }
                }else {
                    cur++;
                    if(cur==n+1) {
                        cur=1;
                    }
                }
            }
        }else {
            for(int i=0;i<cmd.length();i++) {
                if(cmd.charAt(i)=='U') {
                    cur--;
                    if(cur==0) {
                        cur=n;
                        first=n-3;
                    }
                    if(cur==first-1) {
                        first--;
                    }
                }else {
                    cur++;
                    if(cur==first+4) {
                        first++;
                    }
                    if(cur==n+1) {
                        cur=1;
                        first=1;
                    }
                }
            }
        }
        if(n<=4) {
            for(int i=0;i<n;i++) {
                System.out.print(first+++" ");
            }
        }else {
            System.out.print(first+" "+(first+1)+" "+(first+2)+" "+(first+3));
        }
        System.out.println();
        System.out.print(cur);
    }
}