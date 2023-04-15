public class HeapSort {
    public static void heapSort(int[] array) {
        createBigHeap(array);
        int end=array.length-1;
        while (end>0) {
            swap(array,0,end);
            shiftDown(array,0,end);
            end--;
        }
    }

    private static void createBigHeap(int[] array) {
        for (int parent = (array.length-1-1)/2; parent >=0 ; parent--) {
            shiftDown(array,parent,array.length);
        }
    }

    private static void shiftDown(int[] array, int parent, int length) {
        int child=2*parent+1;
        while (child<length) {
            if(child+1<length&&array[child]<array[child+1]) {
                child++;
            }
            if (array[child]>array[parent]) {
                swap(array,child,parent);
                parent=child;
                child=2*parent+1;
            }else {
                break;
            }
        }
    }

    private static void swap(int[] array, int child, int parent) {
        int tmp=array[child];
        array[child]=array[parent];
        array[parent]=tmp;
    }

    public static void main(String[] args) {
        int[] array=new int[]{2,6,7,9,5,3,1,0};
        heapSort(array);
        for (int num:array) {
            System.out.print(num+" ");
        }
    }
}
