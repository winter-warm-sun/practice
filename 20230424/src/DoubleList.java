public class DoubleList {
    // 虚拟头尾节点
    private Node head,tail;
    // 链表元素数
    private int size;

    public DoubleList() {
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
        size=0;
    }
    // 在链表尾部添加节点x
    public void addLast(Node node) {
        node.prev=tail.prev;
        node.next=tail;
        tail.prev.next=node;
        tail.prev=node;
        size++;
    }
    // 删除链表中的节点
    public void remove(Node node) {
        node.prev.next=node.next;
        node.next.prev=node.prev;
        size--;
    }
    // 删除链表中的第一个节点，并返回该节点
    public Node removeFirst() {
        if(head.next==tail) {
            return null;
        }
        Node first=head.next;
        remove(first);
        return first;
    }
    // 返回链表长度
    public int size() {
        return size;
    }
}
