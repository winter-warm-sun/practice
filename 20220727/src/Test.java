import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        queue.add(3);
        queue.add(4);
    }
}
