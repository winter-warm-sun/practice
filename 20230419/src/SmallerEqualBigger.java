public class SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value=data;
        }
    }

    public static Node listPartition(Node head,int pivot) {
        Node sH=null;  // small head
        Node sT=null;  // small tail
        Node eH=null;  // equal head
        Node eT=null;  // equal tail
        Node mH=null;  // big head
        Node mT=null;  // big tail
        Node next=null;  // save next node
        while (head!=null) {
            next=head.next;
            head.next=null;
            if(head.value<pivot) {
                if(sH==null) {
                    sH=head;
                    sT=head;
                }else {
                    sT.next=head;
                    sT=sT.next;
                }
            }else if(head.value==pivot) {
                if(eH==null) {
                    eH=head;
                    eT=head;
                }else {
                    eH.next=head;
                    eH=eH.next;
                }
            }else {
                if(mH==null) {
                    mH=head;
                    mT=head;
                }else {
                    mT.next=head;
                    mT=mT.next;
                }
            }
            head=next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if(sT!=null) { // 如果有小于区域
            sT.next=eH;
            eT=eT==null?sT:eT;
        }
        if(eT!=null) {
            eT.next=mH;
        }
        return sH!=null?sH:(eH!=null?eH:mH);
    }
}
