package Test1;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        int c=scanner.nextInt();
        int d=scanner.nextInt();
        int A=(a+c)/2;
        int B=(b+d)/2;
        int C=d-B;
        if(A-B!=a||B-C!=b||A+B!=c||B+C!=d) {
            System.out.println("No");
            return;
        }
        if(!(A+B+C>=-90&&A+B+C<=90)) {
            System.out.println("No");
            return;
        }
        System.out.println(A+" "+B+" "+C);
    }
}
