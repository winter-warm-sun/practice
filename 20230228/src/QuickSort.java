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

    private static void swap(int[] array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    private static void quick(int[] array,int left,int right) {
        // 递归结束的终止条件
        if(left>=right) {
            return;
        }

        // 小区间使用直接插入排序：优化递归深度
        if(right-left+1<=7) {
            insertSort(array,left,right);
            return;
        }
        // 三数取中(调整基准值，防止数组因有序而导致递归深度太深)
        int index=midNumIndex(array,left,right);
        swap(array,left,index);

        int pivot=partitionHoare(array,left,right);
        quick(array,left,pivot-1);
        quick(array,pivot+1,right);
    }

    private static void insertSort(int[] array,int start,int end) {
        for (int i=start+1;i<=end;i++) {
            int tmp=array[i];
            int j=i-1;
            for (;j>=start;j--) {
                if(array[j]>tmp) {
                    array[j+1]=array[j];
                }else {
                    break;
                }
            }
            array[j+1]=tmp;
        }
    }

    private static int midNumIndex(int[] array,int left,int right) {
        int mid=(left+right)/2;
        if(array[left]<array[right]) {
            if(array[mid]<array[left]) {
                return left;
            }else if(array[mid]>array[right]) {
                return right;
            }else {
                return mid;
            }
        }else {
            if(array[mid]<array[right]) {
                return right;
            }else if(array[mid]>array[left]) {
                return left;
            }else {
                return mid;
            }
        }
    }

    public static void quickSort(int[] array) {
        quick(array,0,array.length-1);
    }
}
