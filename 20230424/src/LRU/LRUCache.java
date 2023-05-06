package LRU;

import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer,Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCache(int capacity) {
        this.cap=capacity;
        map=new HashMap<>();
        cache=new DoubleList();
    }

    private void makeRecently(int key) {
        Node node=map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    private void addRecently(int key,int val) {
        Node node=new Node(key,val);
        cache.addLast(node);
        map.put(key,node);
    }

    private void deleteKey(int key) {
        Node node=map.get(key);
        cache.remove(node);
        map.remove(key);
    }

    private void removeLeastRecently() {
        Node node=cache.removeFirst();
        int key= node.key;
        map.remove(key);
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return map.get(key).val;
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
}
