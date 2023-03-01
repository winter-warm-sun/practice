public class MergeSort {
    private static void merge(int[] array,int start,int end,int midIndex) {
        int[] tmpArr=new int[end-start+1];
        int k=0;
        int s1=start;
        int s2=midIndex+1;
        while (s1<=midIndex&&s2<=end) {
            if(array[s1]<array[s2]) {
                tmpArr[k++]=array[s1++];
            }else {
                tmpArr[k++]=array[s2++];
            }
        }
        while (s1<=midIndex) {
            tmpArr[k++]=array[s1++];
        }
        while (s2<=end) {
            tmpArr[k++]=array[s2++];
        }
        for(int i=0;i<k;i++) {
            array[i+start]=tmpArr[i];
        }
    }

    private static void mergeSortFunc(int[] array,int left,int right) {
        if(left>=right) {
            return;
        }
        int midIndex=(left+right)/2;
        mergeSortFunc(array,left,midIndex);
        mergeSortFunc(array,midIndex+1,right);
        merge(array,left,right,midIndex);
    }

    public static void mergeSort(int[] array) {
        mergeSortFunc(array,0,array.length-1);
    }
}
