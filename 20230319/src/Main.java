import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        List<String> list=new ArrayList<>();
        list.add(s);
        for(int i=0;i<s.length()-1;i++) {
            list.add(swap(i,s));
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(list.get(0));
    }
    static String swap(int index,String s) {
        StringBuffer sb=new StringBuffer(s.substring(0,index));
        sb.append(s.charAt(index+1));
        sb.append(s.charAt(index));
        sb.append(s.substring(index+2));
        return sb.toString();
    }
}
