import java.util.Arrays;

public class MyStack {
    public int[] elem;
    public int usedSize;
    public static final int DEFAULT_CAPACITY=10;

    public MyStack() {
        elem=new int[DEFAULT_CAPACITY];
    }

    /**
     * 入栈
     * @param val
     */
    public void push(int val) {
        //先判断栈是否满了
        if(isFull()) {
            elem= Arrays.copyOf(elem,2*elem.length);
        }
        elem[usedSize++]=val;
    }

    /**
     * 判断当前栈是否满了
     * @return
     */
    public boolean isFull() {
        if(usedSize==elem.length) {
            return true;
        }
        return false;
    }
    /**
     * 删除栈顶元素
     */
    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("栈空了");
        }
        int val= elem[usedSize-1];
        usedSize--;
        return val;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return usedSize==0;
    }

    /**
     * 获取栈顶元素但不删除
     * @return
     */
    public int peek() {
        if(isEmpty()) {
            throw new RuntimeException("栈为空了！");
        }
        return elem[usedSize-1];
    }
}
