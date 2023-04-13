public class MergeSort {
    public static void mergeSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr,int L,int R) {
        if(L==R) {
            return;
        }
        int M=L+((R-L)>>1);
        process(arr,L,M);
        process(arr,M+1,R);
        merge(arr,L,M,R);
    }
    public static void merge(int[] arr,int L,int M,int R) {
        int[] help=new int[R-L+1];
        int p1=L;
        int p2=M+1;
        int index=0;
        while (p1<=M&&p2<=R) {
            help[index++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=M) {
            help[index++]=arr[p1++];
        }
        while (p2<=R) {
            help[index++]=arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L+i]=help[i];
        }
    }
}
