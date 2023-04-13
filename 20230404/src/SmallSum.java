public class SmallSum {
    public static int sum(int[] arr) {
        if(arr==null||arr.length<2) {
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr, int L, int R) {
        if(L==R) {
            return 0;
        }
        int M=L+((R-L)>>1);
        return process(arr,L,M)+
                process(arr,M+1,R)+
                merge(arr,L,M,R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int[] help=new int[R-L+1];
        int p1=L;
        int p2=M+1;
        int index=0;
        int res=0;
        while (p1<=M&&p2<=R) {
            res+=arr[p1]<arr[p2]?(R-p2+1)*arr[p1]:0;
            help[index++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=M) {
            help[index++]=arr[p1++];
        }
        while (p2<=R) {
            help[index++]=arr[p2++];
        }
        for (int i = 0; i < arr.length; i++) {
            arr[L+i]=help[i];
        }
        return res;
    }
}
