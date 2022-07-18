import java.util.Comparator;
import java.util.PriorityQueue;

public class Test1 {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(3);
        priorityQueue.offer(4);
        priorityQueue.offer(5);
        System.out.println("  ");
    }
}
