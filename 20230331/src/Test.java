public class Test {
    public static class Node {
        public int value;
        public Node next;
        public Node(int value) {
            this.value=value;
        }
    }

    public static Node reverseLinkedList(Node head) {
        Node pre=null;
        Node next=null;
        while (head!=null) {
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value=value;
        }
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre=null;
        DoubleNode next=null;
        while (head!=null) {
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;
        }
        return pre;
    }

    public static Node removeElements(Node head,int value) {
        if(head==null) {
            return null;
        }
        Node dummyNode=new Node(0);
        dummyNode.next=head;
        Node pre=dummyNode;
        Node cur=head;
        while (cur!=null) {
            if(cur.value==value) {
                pre.next=cur.next;
                cur=cur.next;
            }else {
                pre=cur;
                cur=cur.next;
            }
        }
        return dummyNode.next;
    }
}

