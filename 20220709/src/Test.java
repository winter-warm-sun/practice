import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        Deque<Integer> deque1=new LinkedList<>();
        Deque<Integer> deque2=new LinkedList<>();
        deque1.offer(1);
        deque1.offer(2);
        System.out.println(deque1.peek());
        deque2.push(1);
        deque2.push(2);
        System.out.println(deque2.peek());
    }
}
