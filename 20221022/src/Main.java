import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        List<Integer> list=new ArrayList<>();
        int index=0;
        for(int i=2;i<1000;i++) {
            boolean flag=true;
            for (int j = 2; j <=Math.sqrt(i); j++) {
                if(i%j==0) {
                    flag=false;
                    break;
                }
            }
            if(flag) {
                list.add(i);
            }
        }

        int n= scanner.nextInt();
        int i=n/2;
        while (i>0) {
            if(list.contains(i)&&list.contains(n-i)) {
                System.out.println(i);
                System.out.println(n-i);
                break;
            }
            i--;
        }
    }
}
