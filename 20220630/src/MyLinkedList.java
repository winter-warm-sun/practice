class Node {
    int val;
    Node next;
    public Node(int val) {
        this.val=val;
    }
}
class MyLinkedList {
    int val;
    Node head;
    public MyLinkedList() {
    }

    public int get(int index) {
        if(index<0||index>=size())
            return -1;
        Node cur=this.head;
        int count=0;
        while(count!=index) {
            cur=cur.next;
            count++;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        Node node=new Node(val);
        if(head==null) {
            head=node;
            return;
        }
        node.next=this.head;
        this.head=node;
    }

    public void addAtTail(int val) {
        Node node=new Node(val);
        if(head==null) {
            head=node;
            return;
        }
        Node cur=this.head;
        while(cur.next!=null) {
            cur=cur.next;
        }
        cur.next=node;
    }

    private Node searchIndex(int index) {
        Node cur=this.head;
        int count=0;
        while (count!=index-1) {
            count++;
            cur=cur.next;
        }
        return cur;
    }

    public void addAtIndex(int index, int val) {
        if(index<=0) {
            addAtHead(val);
            return;
        }
        if(index==size()) {
            addAtTail(val);
            return;
        }
        if(index>size())
            return;
        Node cur=searchIndex(index);
        Node node=new Node(val);
        node.next=cur.next;
        cur.next=node;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size()) {
            return;
        }
        if(index==0) {
            head=head.next;
        }else {
            Node cur=searchIndex(index);
            cur.next=cur.next.next;
        }
    }

    public int size() {
        int count=0;
        Node cur=head;
        while(cur!=null) {
            count++;
            cur=cur.next;
        }
        return count;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */