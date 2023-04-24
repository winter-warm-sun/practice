import java.util.LinkedHashMap;

public class LRUCache2 {
    int cap;
    LinkedHashMap<Integer,Integer> cache=new LinkedHashMap<>();
    public LRUCache2(int capacity) {
        this.cap=capacity;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key,int val) {
        if(cache.containsKey(key)) {
            // 修改key的值
            cache.put(key,val);
            makeRecently(key);
            return;
        }
        if(cache.size()>=this.cap) {
            // 链表头部就是最久未使用的key
            int oldestKey=cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的key添加到链表尾部
        cache.put(key,val);
    }
    private void makeRecently(int key) {
        int val=cache.get(key);
        // 删除key，重新插入到队尾
        cache.remove(key);
        cache.put(key,val);
    }
}
