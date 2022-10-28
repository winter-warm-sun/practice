import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String s=in.nextLine();
        Queue<String> queue=new LinkedList<>();
        int start=0,end=0;
        while (end<s.length()) {
            if(s.charAt(end)=='\"') {
                end++;
                start=end;
                while (end<s.length()&&s.charAt(end)!='\"') {
                    end++;
                }
                queue.offer(s.substring(start,end));
                end++;
                continue;
            }
            while (end<s.length()&&s.charAt(end)==' ') {
                end++;
            }
            start=end;
            while (end<s.length()&&s.charAt(end)!=' ') {
                end++;
            }
            queue.offer(s.substring(start,end));
        }
        System.out.println(queue.size());
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}