public class HashBuck {
    static class Node {
        public int key;
        public int val;
        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    public Node[] array;
    public int usedSize;

    public static final float DEFAULT_LOAD_FACTOR=0.75F;

    public HashBuck() {
        this.array = new Node[10];
        this.usedSize = 0;
    }

    public void put(int key,int val) {
        Node node=new Node(key,val);
        int index=key%array.length;

        Node cur=array[index];
        while (cur!=null) {
            if(cur.key==key) {
                cur.val=val;
                return;
            }
        }
        //头插法
        node.next=array[index];
        array[index]=node;
        usedSize++;
        if(loadFactor()>DEFAULT_LOAD_FACTOR) {
            grow();
        }
    }
    private float loadFactor() {
        return usedSize*1.0f/array.length;
    }

    private void grow() {
        Node[] newArray=new Node[2*array.length];
        for (int i = 0; i < array.length; i++) {
            Node cur = array[i];
            while (cur != null) {
                Node curNext = cur.next;
                //找到新的位置
                int index = cur.key % newArray.length;
                cur.next = newArray[index];
                newArray[index] = cur;
                //这样写对吗？
                //cur = cur.next;
                cur = curNext;
            }
        }
        this.array = newArray;
    }
}
