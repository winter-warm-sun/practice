class Node {
    public int val;
    public Node next;
    public Node prev;
    public Node(int val) {
        this.val = val;
    }
}
public class MyLinkedList {
    public Node head;
    public Node tail;
    public void addFirst(int data) {

    }
    //1.头插法
    public void addLast(int data) {
        Node node=new Node(data);
        if(this.head==null) {
            this.head=node;
            this.tail=node;
        }else {
            node.next=head;
            head.prev=node;
            head=node;
        }
    }

    //2.尾插法
    public void display(int data) {
        Node node=new Node(data);
        if(this.head==null) {
            this.head = node;
            this.tail = node;
        }else {
            tail.next=node;
            node.prev=tail;
            tail=node;
        }
    }
    //3.打印链表
    public void display() {
        Node cur=head;
        while (cur!=null) {
            System.out.print(cur.val+" ");
            cur=cur.next;
        }
    }
    //4.查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        Node cur=head;
        while (cur!=null) {
            if(cur.val==key)
                return true;
            cur=cur.next;
        }
        return false;
    }
    //5.求链表长度
    public int size() {
        int a=0;
        Node cur=this.head;
        while(cur!=null) {
            a++;
            cur=cur.next;
        }
        return a;
    }
    //6.任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index,int data) {
        if(index<0||index>size())
            return;
        if(index==0)
            addFirst(data);
        if(index==size())
            addLast(data);
        Node cur=head;
        int a=0;
        while (a!=index) {
            cur=cur.next;
            a++;
        }
        Node node=new Node(data);
        node.prev=cur.prev;
        node.next=cur;
        cur.prev.next=node;
        cur.prev=node;
    }
    //7.删除第一次出现关键字为key的节点
    public void remove(int key) {
        Node cur=this.head;
        while (cur!=null) {
            if(cur.val==key) {
                if(cur==head) {
                    head=null;
                }else {
                    cur.prev.next=cur.next;
                    if(cur.next!=null)
                    cur.next.prev=cur.next;
                    else
                        this.tail=cur.prev;
                }
                return;
            }else
                cur=cur.next;
        }
    }
    //8.删除所有值为key的节点
    public void removeAllKey(int key) {
        Node cur=this.head;
        while(cur!=null) {
            if(cur.val==key) {//如果找到关键字key
                if(cur==this.head) {//头节点的data为key
                    this.head=cur.next;//头节点后移完成头节点删除
                    if(this.head!=null)//防止空指针异常
                        this.head.prev=null;
                }else {//中间找到key
                    cur.prev.next=cur.next;
                    if(cur.next!=null)
                        cur.next.prev=cur.prev;
                    else//如果cur.next==null，尾节点即为所需删除节点
                        this.tail=cur.prev;
                }
            }
            cur=cur.next;//如果没有进if语句中，cur继续往后遍历
        }

    }
    //9.清空链表
    public void clear() {
        while (this.head!=null) {
            Node cur=head.next;
            this.head.prev=null;
            this.head.next=null;
            this.head=cur;
        }
        this.tail=null;
    }



}
