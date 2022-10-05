import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int count=0;
        for(int i=2;i<=n;i++) {
            int sum=0;
            for(int j=1;j<i;j++) {
                if(i%j==0) {
                    sum+=j;
                }
            }
            if(sum==i) {
                count++;
            }
        }
        System.out.print(count);
    }
}