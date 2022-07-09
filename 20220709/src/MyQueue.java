import java.util.Deque;

public class MyQueue {
    static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }
    public Node head;//队列的头
    public Node tail;//队列的尾

    /**
     * 入队操作
     * @param val
     */
    public void offer(int val) {
        Node node=new Node(val);
        if(head==null) {
            head=node;
            tail=node;
        }else {
            tail.next=node;
            tail=tail.next;
        }
    }
    /**
     * 出队操作
     */
    public int poll() {
        if(head==null)
            throw new RuntimeException("队列为空！");
        int val= head.val;;
        if(head.next==null)
            head=tail=null;
        else
            head=head.next;
        return val;
    }
    /**
     * 查看队头元素
     */
    public int peek() {
        if(head==null) {
            throw new RuntimeException("队列为空！");
        }
        return head.val;
    }
}
