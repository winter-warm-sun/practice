public class HeapSort {
    private static void createBigHeap(int[] array) {
        // 先找到最后一棵子树的父节点，让每棵子树成为大顶堆
        for (int parent=(array.length-1-1)/2;parent>=0;parent--) {

        }
    }

    private static void shiftDown(int[] array,int parent,int len) {
        // 保证有左孩子
        int child=2*parent+1;
        while (child<len) {
            // 如果有右孩子,左右孩子比较，child记录较大值的下标
            if(child+1<len&&array[child]<array[child+1]) {
                child++;
            }
            if(array[child]>array[parent]) {
                swap(array,child,parent);
                // 继续向下调整
                parent=child;
                child=2*parent+1;
            }else {
                break;
            }
        }
    }

    private static void swap(int[] array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    public static void heapSort(int[] array) {
        createBigHeap(array);
        int end=array.length-1;
        while (end>=0) {
            swap(array,0,end);
            shiftDown(array,0,end);
            end--;
        }
    }
}
