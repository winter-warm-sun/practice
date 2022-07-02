public class Test {
    public static void main(String[] args) {
        MyLinkedList LinkedList=new MyLinkedList();
        LinkedList.addAtHead(7);
        LinkedList.addAtHead(2);
        LinkedList.addAtHead(1);
        LinkedList.addAtIndex(3,0);   //链表变为1-> 2-> 3
        LinkedList.deleteAtIndex(2);
        LinkedList.addAtHead(6);
        LinkedList.addAtTail(4);
        System.out.println(LinkedList.get(4));
        LinkedList.addAtHead(4);
        LinkedList.addAtIndex(5,0);
        LinkedList.addAtHead(6);
    }
}
