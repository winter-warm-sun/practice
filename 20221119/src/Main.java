import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()) {
            int n=sc.nextInt();
            int count=0;
            for(int i=2;i*i<=n;i++) {
                if(n%i==0) {
                    count++;
                }
                while(n%i==0) {
                    n/=i;
                }
            }
            if(n!=1) {
                count++;
            }
            System.out.println(count);
        }
    }
}