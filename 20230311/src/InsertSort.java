public class InsertSort {
    public static void insertSort(int[] array) {
        // 只有一个元素时，一定是有序的,所以遍历从第二个元素开始
        for(int i=1;i<array.length;i++) {
            int tmp=array[i];
            int j=i-1;
            for(;j>=0;j--) {
                if(array[j]>tmp) {
                    array[j+1]=array[j];
                }else {
                    break;
                }
            }
            array[j+1]=tmp;
        }
    }
}
