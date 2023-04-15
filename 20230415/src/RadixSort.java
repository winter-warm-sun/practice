public class RadixSort {
    public static void radixSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    private static int maxbits(int[] arr) {
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
        }
        int res=0;
        while (max!=0) {
            res++;
            max/=10;
        }
        return res;
    }

    public static void radixSort(int[] arr,int L,int R,int digit) {
        final int radix=10;
        int i=0,j=0;
        int[] help=new int[R-L+1];
        for (int d = 1; d <=digit ; d++) {// 有多少位就进出多少次
            int[] count=new int[radix];
            for (i = L; i <=R ; i++) {
                j=
            }
        }
    }
}
