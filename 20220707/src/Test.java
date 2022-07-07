import java.util.Stack;
class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}

public class Test {
    //递归方式
    public void printList1(Node head) {
        if(null!=head) {
            printList1(head.next);
            System.out.println(head.val+" ");
        }
    }
    //循环方式
    public void printList2(Node head) {
        if(head==null)
            return;
        Stack<Node> stack=new Stack<>();
        //将链表中的结点保存在栈中
        Node cur=head;
        while (cur!=null) {
            stack.push(cur);
            cur=cur.next;
        }
        //将栈中元素出栈
        while (!stack.empty()) {
            System.out.println(stack.pop().val+" ");
        }
    }
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        stack.push(12);
        stack.push(23);
        stack.push(34);
        stack.push(45);
        int val=stack.peek();//获取栈顶元素，但是不删除
        System.out.println(val);//45
        int val2=stack.pop();//删除并返回栈顶元素
        System.out.println(val2);

        val=stack.peek();//获取栈顶元素，但是不删除
        System.out.println(val);//34
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.empty());
        System.out.println(stack.size());
    }
}
