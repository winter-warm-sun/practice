import java.util.TreeMap;

public class ReverseList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
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
            this.value = value;
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

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "我是3");
        treeMap.put(4, "我是4");
        treeMap.put(8, "我是8");
        treeMap.put(5, "我是5");
        treeMap.put(7, "我是7");
        treeMap.put(1, "我是1");
        treeMap.put(2, "我是2");

        System.out.println(treeMap.containsKey(1));
        System.out.println(treeMap.containsKey(10));

        System.out.println(treeMap.get(4));
        System.out.println(treeMap.get(10));

        treeMap.put(4, "他是4");
        System.out.println(treeMap.get(4));

        // treeMap.remove(4);
        System.out.println(treeMap.get(4));

        System.out.println("新鲜：");

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        // <= 4的最大值
        System.out.println(treeMap.floorKey(4));
        // >= 4的最小值
        System.out.println(treeMap.ceilingKey(4));
        // O(logN)
    }

    public static int getMax(int[] arr) {
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int L,int R) {
        if(L==R) {
            return arr[L];
        }
        int mid=L+((R-L)>>1);
        int leftMax=process(arr,L,mid);
        int rightMax=process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }
}
