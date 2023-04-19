import java.util.Stack;

public class IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack=new Stack<>();
        Node cur=head;
        while (cur!=null) {
            stack.push(cur);
            cur=cur.next;
        }
        while (head!=null) {
            if(head.value!=stack.pop().value) {
                return false;
            }
            head=head.next;
        }
        return true;
    }

    public boolean isPalindrome(Node head) {
        if(head==null||head.next==null) {
            return true;
        }
        Node fast=head;
        Node slow=head;
        while(fast.next!=null&&fast.next.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
        }
        Node cur=slow.next;
        slow.next=null;
        Node pre=slow;
        Node next=null;
        while(cur!=null) {
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        boolean res=true;
        while(head!=null&&pre!=null) {
            if(head.value!=pre.value) {
                res=false;
                break;
            }
            head=head.next;
            pre=pre.next;
        }
        return res;
    }
}
