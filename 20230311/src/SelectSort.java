public class SelectSort {
    public static void selectSort(int[] array) {
        for (int i=0;i<array.length-1;i++) {
            int minIndex=i;
            for (int j=i+1;j<array.length;j++) {
                if(array[j]<array[minIndex]) {
                    minIndex=j;
                }
            }
            swap(array,minIndex,i);
        }
    }

    private static void swap(int[] array, int minIndex, int i) {
        int tmp=array[minIndex];
        array[minIndex]=array[i];
        array[i]=tmp;
    }

    public static void selectSort2(int[] array) {
        int left=0;
        int right=array.length-1;
        while (left<right) {
            int minIndex=left;
            int maxIndex=right;
            for (int i=left+1;i<=right;i++) {
                if(array[i]<array[minIndex]) {
                    minIndex=i;
                }
                if(array[i]>array[maxIndex]) {
                    maxIndex=i;
                }
            }
            swap(array,left,minIndex);
            // 修正max的下标
            if(left==maxIndex) {
                maxIndex=minIndex;
            }
            swap(array,right,maxIndex);
            left++;
            right--;
        }
    }
}
