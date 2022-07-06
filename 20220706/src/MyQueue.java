import java.util.Stack;

class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;
    public MyQueue() {
        stackIn=new Stack<>();
        stackOut=new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        fun();
        return stackOut.pop();
    }

    public int peek() {
        fun();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty()&&stackOut.isEmpty();
    }
    private void fun() {
        if(!stackOut.isEmpty()) return;
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }
}