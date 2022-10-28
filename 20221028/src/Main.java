import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String s=in.nextLine();
        s=s.toUpperCase();
        char ch=in.next().toUpperCase().charAt(0);
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==ch) {
                count++;
            }
        }
        System.out.println(count);
    }
}