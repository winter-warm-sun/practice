public class DoubleList {
    private Node head,tail;
    private int size;

    public DoubleList() {
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
        size=0;
    }
    public void addLast(Node node) {
        node.next=tail;
        node.prev=tail.prev;
        tail.prev.next=node;
        tail.prev=node;
        size++;
    }
    public void remove(Node node) {
        node.prev.next=node.next;
        node.next.prev=node.prev;
        size--;
    }
    public Node removeFirst() {
        if(head.next==tail) {
            return null;
        }
        Node first=head.next;
        remove(first);
        return first;
    }
    public int size() {
        return size;
    }
}
