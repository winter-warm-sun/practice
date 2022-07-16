import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Heap {
    public int[] elem;
    public int usedSize;//当前堆中有效元素的个数

    public Heap() {
        this.elem=new int[10];
    }
    public void initArray(int[] array) {
        elem= Arrays.copyOf(array,array.length);
        usedSize=elem.length;
    }

    /**
     * 建堆
     * 时间复杂度：O(N)
     */
    public void createHeap() {
        for (int parent = (usedSize-1-1); parent >=0 ; parent--) {
            shiftDown(parent,usedSize);
        }
    }

    /**
     * 向下调整
     * @param parent 每棵子树的根结点下标
     * @param len 每棵子树的结束位置(usedSize)
     */
    private void shiftDown(int parent,int len) {
        int child=2*parent+1;
        //len(usedSize)记录的是每棵子树的结束位置，只有当child<len时才需要做比较，然后向下调整
        while (child<len) {
            //存在右孩子的情况下，比较左右孩子的大小，child记录较大值的下标
            if(child+1<len&&elem[child]<elem[child+1]) {
                child++;
            }
            //此时child记录的是孩子中的较大值，再去与父节点进行比较
            if(elem[child]>elem[parent]) {
                swap(elem,child,parent);
                //向下调整，让parent到child的位置，继续往下做比较
                parent=child;
                child=2*parent+1;
            }else {
                //如果走到else，说明此时该子树符合大顶堆结构，不需要再做向下调整，直接跳出循环即可
                break;
            }
        }
    }
    /**
     * 交换两结点
     */
    private void swap(int[]array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    /**
     * 插入元素
     * @param x
     */
    public void offer(int x) {
        if(isFull()) {
            elem=Arrays.copyOf(elem,elem.length*2);
        }
        this.elem[usedSize]=x;
        usedSize++;

    }
    public boolean isFull() {
        return usedSize==elem.length;
    }

    /**
     * 向上调整
     * @param child 子结点下标
     */
    private void shiftUp(int child) {
        //找到其父结点
        int parent=(child-1)/2;
        //向上调整一直到根结点结束
        while (child>0) {
            //判读子结点与父结点大小
            if(elem[child]>elem[parent]) {
                swap(elem,child,parent);
                child=parent;
                parent=(child-1)/2;
            }else {
                //若不需要调整，则直接跳出循环
                break;
            }
        }
    }

    /**
     * 删除元素（出队）
     * @return
     */
    public int poll() {
        if(isEmpty()) {
            return -1;
        }
        int old=elem[0];
        PriorityQueue priorityQueue=new PriorityQueue<>();
        priorityQueue.add();
        //交换堆顶与堆尾元素
        swap(elem,0,usedSize-1);
        //删除堆尾元素
        usedSize--;
        //将堆顶元素向下调整
        shiftDown(0,usedSize);
        return old;
    }
    public boolean isEmpty() {
        return usedSize==0;
    }

}
