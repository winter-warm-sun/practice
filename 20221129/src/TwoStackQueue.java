import java.util.Stack;

public class TwoStackQueue {
    public Stack<Integer> stackIn;
    public Stack<Integer> stackOut;

    public TwoStackQueue() {
        stackIn=new Stack<>();
        stackOut=new Stack<>();
    }

    public void offer(int value) {
        //入队列时直接入入栈
        stackIn.push(value);
    }

    public boolean empty() {
        //当两栈均为空时，模拟队列为空
        return stackIn.isEmpty()&&stackOut.isEmpty();
    }
    private void fun() {
        //当出数据时，如果出栈不为空，直接出出栈中的数据即可
        //如果出栈为空，则将入栈中的数出到出栈中，就模拟了队列的先进先出的顺序
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
