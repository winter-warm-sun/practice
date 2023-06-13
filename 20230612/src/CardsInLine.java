import javax.print.DocFlavor;

public class CardsInLine {
    public static int win1(int[] arr) {
        if(arr==null||arr.length==0) {
            return 0;
        }
        int first=f1(arr,0,arr.length-1);
        int second=g1(arr,0,arr.length-1);
        return Math.max(first,second);
    }

    public static int f1(int[] arr, int L, int R) {
        if(L==R) {
            return arr[L];
        }
        int p1=arr[L]+g1(arr,L+1,R);
        int p2=arr[R]+g1(arr,L,R-1);
        return Math.max(p1,p2);
    }

    public static int g1(int[] arr, int L, int R) {
        if(L==R) {
            return 0;
        }
        int p1=f1(arr,L+1,R);
        int p2=f1(arr,L,R-1);
        return Math.min(p1,p2);
    }

    public static int win2(int[] arr) {
        if(arr==null||arr.length==0) {
            return 0;
        }
        int N=arr.length;
        int[][] fmap=new int[N][N];
        int[][] gmap=new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i]=arr[i];
        }
        for (int i = 1; i < N; i++) {
            int L=0;
            int R=i;
            while (L<N) {
                fmap[L][R]=Math.max(arr[L]+gmap[L+1][R],arr[R]+gmap[L][R-1]);
                gmap[L][R]=Math.min(fmap[L+1][R],gmap[L][R-1]);
                L++;
                R++;
            }
        }
        return Math.max(fmap[0][N-1],gmap[0][N-1]);
    }

}
