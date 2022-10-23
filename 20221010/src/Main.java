import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        String[] ss=s.split(" ");
        List<String> list=new ArrayList<>();
        for(int i=0;i<ss.length;i++) {
            list.add(ss[i]);
        }
        StringBuffer sb=new StringBuffer();
        for(int i=list.size()-1;i>=0;i--) {
            sb.append(list.get(i)+" ");
        }
        System.out.print(sb.toString());
    }
}