import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeapGreater<T> {
    private ArrayList<T> heap;
    private HashMap<T,Integer> indexMap;  // 用于加强堆结构中的反向索引操作
    private int heapSize;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> comp) {
        heap=new ArrayList<>();
        indexMap=new HashMap<>();
        heapSize=0;
        this.comp = comp;
    }

    // 判断堆是否为空
    public boolean isEmpty() {
        return heapSize==0;
    }
    // 返回堆大小
    public int size() {
        return heapSize;
    }
    // 判断堆中是否包含某个元素
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }
    // 返回堆顶元素
    public T peek() {
        return heap.get(0);
    }
    // 堆中新增元素
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj,heapSize);
        heapInsert(heapSize);
        heapSize++;
    }
    // 堆中插入新元素，向上调整新元素
    private void heapInsert(int child) {
        int parent=(child-1)/2;
        while (child>0) {
            if(comp.compare(heap.get(child),heap.get(parent))<0) {
                swap(child,parent);
                child=parent;
                parent=(child-1)/2;
            }else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T o1=heap.get(i);
        T o2=heap.get(j);
        heap.set(i,o2);
        heap.set(j,o1);
        indexMap.put(o2,i);
        indexMap.put(o1,j);
    }
    // 弹出堆顶元素
    public T pop() {
        T ans=heap.get(0);
        swap(0,heapSize-1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        shiftDown(0);
        return ans;
    }
    // 小根堆向下调整
    private void shiftDown(int parent) {
        int child=2*parent+1;
        while (child<heapSize) {
            if(child+1<heapSize&&comp.compare(heap.get(child+1),heap.get(child))<0) {
                child++;
            }
            if(comp.compare(heap.get(child),heap.get(parent))<0) {
                swap(child,parent);
                parent=child;
                child=2*parent+1;
            }else {
                break;
            }
        }
    }
    // 去除某个元素
    public void remove(T obj) {
        T replace=heap.get(heapSize-1);
        int index=indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if(obj!=replace) {
            heap.set(index,replace);
            indexMap.put(replace,index);
            resign(replace);
        }
    }

    private void resign(T obj) {
        heapInsert(indexMap.get(obj));
        shiftDown(indexMap.get(obj));
    }

    // 返回堆上所有元素
    public List<T> getAllElements() {
        List<T> ans=new ArrayList<>();
        for(T c:heap) {
            ans.add(c);
        }
        return ans;
    }
}
