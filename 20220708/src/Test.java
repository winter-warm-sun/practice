import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.peek());//1
        System.out.println(queue.poll());//1
        System.out.println(queue.peek());//2
    }
}
