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

    public Node[] array;//节点数组 用于存储各链表的头结点
    public int usedSize;//记录当前哈希桶中有效数据的个数
    //默认的负载因子
    public static final float DEFAULT_LOAD_FACTOR=0.75F;

    public HashBuck() {
        this.array=new Node[10];
        this.usedSize=0;
    }

    /**
     * 存储key val
     * @param key
     * @param val
     */
    public void put(int key,int val) {
        Node node=new Node(key, val);
        int index=key%array.length;
        Node cur=array[index];
        //先判断桶中是否有相同的关键字，如果有完成value的替换
        while (cur!=null) {
            if(cur.key==key) {
                cur.val=val;
                return;
            }
            cur=cur.next;
        }
        //头插法完成节点的插入
        node.next=array[index];
        array[index]=node;
        usedSize++;
    }

    /**
     * 检查负载因子
     * @return
     */
    private float loadFactor() {
        return usedSize*1.0f/array.length;
    }

    private void grow() {
        Node[] newArray=new Node[2*array.length];
        //遍历原哈希表中的每一个哈希桶，让每个节点都重新哈希
        for (int i = 0; i < array.length; i++) {
            Node cur=array[i];
            while (cur!=null) {
                //获取新的位置
                int index=cur.key% newArray.length;
                Node curNext=cur.next;//记录
                //头插法完成新的哈希
                cur.next=newArray[index];
                newArray[index]=cur;
                //因为需要遍历链表中的所有节点，所以需要提前记录cur.next
                cur=curNext;
            }
        }
        this.array=newArray;
    }

    public int get(int key) {
        int index=key%array.length;
        Node cur=array[index];
        while (cur!=null) {
            if(cur.key==key) {
                return cur.val;
            }
            cur=cur.next;
        }
        return -1;
    }
}
