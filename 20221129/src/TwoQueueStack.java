import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueStack {
    public Queue<Integer> queue1;
    public Queue<Integer> queue2;

    public TwoQueueStack() {
        queue1=new LinkedList<>();
        queue2=new LinkedList<>();
    }

    public void push(int value) {
        queue2.offer(value);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> tmp=queue1;
        queue1=queue2;
        queue2=tmp;
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
