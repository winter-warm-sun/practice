package Test2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        int[] array=new int[N];
        for (int i = 0; i < N; i++) {
            array[i]=scanner.nextInt();
        }
        int sum=array[0];
        int max=array[0];
        for (int i = 1; i < array.length; i++) {
            sum=Math.max(sum+array[i],array[i]);
            if(sum>=max) {
                max=sum;
            }
        }
        System.out.println(sum);
    }
}
