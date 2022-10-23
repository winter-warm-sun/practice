import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        int count=0;
        HashMap<String,Integer> map=new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(!(s.charAt(i)>='0'&&s.charAt(i)<='9')||i==s.length()-1) {
                if(count==0)
                continue;
                else {
                    String ss=s.substring(i-count,i);
                    if(i==s.length()-1) {
                        ss=s.substring(i-count,i+1);
                        count++;
                    }
                    map.put(ss,count);
                    count=0;
                }
            }else {
                count++;
            }
        }
        List<Map.Entry<String,Integer>> list=new ArrayList<>(map.entrySet());
        Collections.sort(list,(o1,o2)-> (o2.getValue())-o1.getValue());
        System.out.println(list.get(0).getKey());
    }
}
