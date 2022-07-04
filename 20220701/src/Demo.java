public class Demo {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        node1.next=node2;
        Node curNext=node2.next;
        node2.next=node1;
        boolean
    }
}
class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}
