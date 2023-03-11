public class MergeSort {
    public static void mergeSort(int[] array) {
        mergeSortFunc(array,0,array.length-1);
    }

    private static void mergeSortFunc(int[] array, int left, int right) {
        if(left>=right) {
            return;
        }
        int mid=(left+right)/2;
        // 分解左边
        mergeSortFunc(array,left,mid);
        // 分解右边
        mergeSortFunc(array,mid+1,right);
        // 进行合并
        merge(array,left,right,mid);
    }

    private static void merge(int[] array, int start, int end, int midIndex) {
        int[] tmpArr=new int[end-start+1];// 开辟临时数组用于存储排好序的数组
        int k=0;
        int s1=start;
        int s2=midIndex+1;
        while (s1<=midIndex&&s2<=end) { // 保证两个区间段都有数
            if(array[s1]<=array[s2]) {
                tmpArr[k++]=array[s1++];
            }else {
                tmpArr[k++]=array[s2++];
            }
        }
        // 当两个归并段中有一段没数据后，把另一段剩余数据全部拷贝到tmpArr数组中去
        while (s1<=midIndex) {
            tmpArr[k++]=array[s1++];
        }
        while (s2<=end) {
            tmpArr[k++]=array[s2++];
        }
        // 把排好序的数字拷贝回原数组
        for(int i=0;i<k;i++) {
            array[i+start]=tmpArr[i];
        }
    }
}
