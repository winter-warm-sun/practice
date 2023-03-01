public class QuickSort {
    private static int partition(int[] array,int start,int end) {
        int i=start;
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

    private static void swap(int[] array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    private static void quick(int[] array,int left,int right) {
        if(left>=right) {
            return;
        }
        int pivot=partition(array,left,right);
        quick(array,left,pivot-1);
        quick(array,pivot,right);
    }

    public static void quickSort(int[] array) {
        quick(array,0,array.length-1);
    }
}