public class QuickSort {
    private static int partitionHoare(int[] array,int start,int end) {
        int i=start;// 先存储好start下标，用于最后的交换
        int key=array[start];
        while (start<end) {
            while (start<end&&array[end]>=key) {
                end--;
            }
            while (start<end&&array[start]<=key) {
                start++;
            }
            swap(array,start,end);
        }
        swap(array,start,i);
        return start;
    }

    private static void quick(int[] array,int left,int right) {
        // 在升序或降序的极端情况下，会出现left>right的情况
        if(left>=right) {
            return;
        }
        int pivot=partitionHoare(array,left,right);
        quick(array,left,pivot-1);
        quick(array,pivot+1,right);
    }

    public static void quickSort(int[] array) {
        quick(array,0,array.length-1);
    }

    private static void swap(int[] array, int start, int end) {
        int tmp=array[start];
        array[start]=array[end];
        array[end]=tmp;
    }
}
