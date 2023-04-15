import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeapGreater<T> {
    private ArrayList<T> heap;
    private HashMap<T,Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<? super T> comp) {
        heap=new ArrayList<>();
        indexMap=new HashMap<>();
        heapSize=0;
        this.comp = comp;
    }

    public boolean isEmpty() {
        return heapSize==0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }
    public T peek() {
        return heap.get(0);
    }
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj,heapSize);
        heapInsert(heapSize);
        heapSize++;
    }

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

    private void swap(int child, int parent) {
        T o1= heap.get(child);
        T o2=heap.get(parent);
        heap.set(child,o2);
        heap.set(parent,o1);
        indexMap.put(o1,parent);
        indexMap.put(o2,child);
    }

    public T pop() {
        T ans=heap.get(0);
        swap(0,heapSize-1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        shiftDown(0);
        return ans;
    }

    private void shiftDown(int parent) {
        int child=2*parent+1;
        while (child<heapSize) {
            if(child+1<heapSize&&comp.compare(heap.get(child),heap.get(child+1))>0) {
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

    public List<T> getAllElements() {
        List<T> ans=new ArrayList<>();
        for(T c:heap) {
            ans.add(c);
        }
        return ans;
    }
}
