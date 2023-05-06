import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer,Node> map;
    private int cap;
    private DoubleList cache;
    public LRUCache(int capacity) {
        this.cap=capacity;
        map=new HashMap<>();
        cache=new DoubleList();
    }
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        makeRecently(key);
        return map.get(key).val;
    }

    private void makeRecently(int key) {
        Node x=map.get(key);
        cache.remove(x);
        cache.addLast(x);
    }

    public void put(int key,int val) {
        if(map.containsKey(key)) {
            deleteKey(key);
            addRecently(key,val);
            return;
        }
        if(cap==cache.size()) {
            removeLeastRecently();
        }
        addRecently(key,val);
    }

    private void removeLeastRecently() {
        Node x=cache.removeFirst();
        int key=x.key;
        map.remove(x);
    }

    private void addRecently(int key, int val) {
        Node x=new Node(key,val);
        cache.addLast(x);
        map.put(key,x);
    }

    private void deleteKey(int key) {
        Node x=map.get(key);
        cache.remove(x);
        map.remove(key);
    }
}
