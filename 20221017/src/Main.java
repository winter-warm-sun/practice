import java.util.Scanner;

public class Main {
    static int[] arr=new int[2];
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        function(n);
        int end=Math.min(Math.abs(n-arr[0]),Math.abs(n-arr[1]));
        System.out.println(end);
    }
    public static void function(int n) {
        int a=0;
        int b=1;
        int c=a+b;
        while (c<n) {
            a=b;
            b=c;
            c=a+b;
        }
        arr[0]=b;
        arr[1]=c;
    }
}
