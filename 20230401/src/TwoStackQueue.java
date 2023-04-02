import java.util.Stack;

public class TwoStackQueue {
    public Stack<Integer> stackIn;
    public Stack<Integer> stackOut;

    public TwoStackQueue() {
        stackIn=new Stack<>();
        stackOut=new Stack<>();
    }

    public void offer(int value) {
        stackIn.push(value);
    }

    public boolean isEmpty() {
        return stackIn.isEmpty()&&stackOut.isEmpty();
    }

    private void fun() {
        if(!stackOut.isEmpty()) return;
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }

    public int poll() {
        fun();
        return stackOut.pop();
    }

    public int peek() {
        fun();
        return stackOut.peek();
    }
}
