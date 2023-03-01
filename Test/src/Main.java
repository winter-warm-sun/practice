import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        List<String> list=new ArrayList<>();
        for(int i=0;i<s.length();i++) {
            if(!(s.charAt(i)>='0'&&s.charAt(i)<='9')){
                s=s.replace(s.charAt(i),' ');
            }
        }
        String[] ss=s.split(" ");
        for (int i=0;i<ss.length;i++) {
            list.add(ss[i]);
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length()-o1.length();
            }
        });
        System.out.println(list.get(0));
    }
}