import java.util.*;

public class Main {
    static Map<Integer,Integer> map=new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        int c1=fib(N);
        int c2=map.get(c1);
        c1=Math.abs(c1-N);
        c2=Math.abs(c2-N);
        int x=c1>c2?c2:c1;
        System.out.println(x);
    }
    public static int fib(int N) {
        int a=0;
        int b=1;
        int c=a+b;
        while (N>c) {
            a=b;
            b=c;
            c=a+b;
        }
        map.put(c,b);
        return c;
    }
}
