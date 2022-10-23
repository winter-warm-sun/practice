package Test2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int M=scanner.nextInt();
        int N=scanner.nextInt();
        StringBuilder sb=new StringBuilder();
        String table="0123456789ABCDEF";
        if(M==0) {
            System.out.println(0);
            return;
        }
        while (M!=0) {
            sb.append(table.charAt(M%N));
            M=M/N;
        }
        sb.reverse();
        System.out.println(sb);
    }
}
