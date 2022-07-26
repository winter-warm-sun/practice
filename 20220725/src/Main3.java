import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Set<Character> set=new HashSet<>();
        while (scanner.hasNextLine()) {
            String s1=scanner.nextLine();
            String s2=scanner.nextLine();
            for (char ch:s2.toUpperCase().toCharArray()) {
                set.add(ch);
            }
            Set<Character> set1=new HashSet<>();
            for (char ch:s1.toUpperCase().toCharArray()) {
                if(!set.contains(ch)&&!set1.contains(ch)) {
                    System.out.print(ch);
                    set1.add(ch);
                }
            }

        }
    }
}
