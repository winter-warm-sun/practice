public class HeapSort {
    private static void createBigHeap(int[] array) {
        for(int parent=(array.length-1-1)/2;parent>=0;parent--) {
            shiftDown(array,parent,array.length);
        }
    }

    private static void shiftDown(int[] array,int parent,int len) {
        int child=2*parent+1;
        while (child<len) {
            if(child+1<len&&array[child]<array[child+1]) {
                child++;
            }
            if(array[child]>array[parent]) {
                swap(array,child,parent);
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
