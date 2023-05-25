import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {
    static class Num{
        char ch;
        int count;

        public Num(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
    public static void main(String[] args) {
        String s="abcdaacc";
        char[] chs=s.toCharArray();
        PriorityQueue<Num> queue=new PriorityQueue<>(new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                return o2.count-o1.count;
            }
        });
        int[] arr=new int[26];
        for(char c:chs) {
            arr[c-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if(arr[i]==0) continue;
            char ch=(char)(i+'a');
            Num num=new Num(ch,arr[i]);
            queue.offer(num);
        }
        for(int i=0;i<3;i++) {
            Num num=queue.poll();
            System.out.println(num.ch);
        }
    }
}
