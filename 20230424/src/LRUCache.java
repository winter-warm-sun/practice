import java.util.HashMap;

public class LRUCache {
    // key->Node(key,val)
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap=capacity;
        map=new HashMap<>();
        cache=new DoubleList();
    }

    /*
    将某个Key提升为最近使用的
     */
    private void makeRecently(int key) {
        Node node=map.get(key);
        // 先从链表中删除这个节点
        cache.remove(node);
        // 重新插到队尾
        cache.addLast(node);
    }

    /*
    添加最近使用的的元素
     */
    private void addRecently(int key,int val) {
        Node node=new Node(key,val);
        // 链表尾部就是最近使用的元素
        cache.addLast(node);
        // 在map中添加key的映射
        map.put(key,node);
    }

    /*
    删除某一个key
     */
    private void deleteKey(int key) {
        Node node=map.get(key);
        // 从链表中删除
        cache.remove(node);
        // 从map中删除
        map.remove(key);
    }

    /*
    删除最久未使用的元素
     */
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node deleteNode=cache.removeFirst();
        // 从map中删除该键值对
        int deleteKey=deleteNode.key;
        map.remove(deleteKey);
    }

    // LRU的GET方法
    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        // 将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    // LRU的PUT方法
    public void put(int key,int val) {
        if(map.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key,val);
            return;
        }
        if(cap==cache.size()) {
            // 删除最久未使用的元素
            removeLeastRecently();
        }
        // 添加为最近使用的元素
        addRecently(key,val);
    }
}
