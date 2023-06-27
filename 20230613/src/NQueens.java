public class NQueens {
    public static int num(int n) {
        if(n<1) {
            return 0;
        }
        int[] record=new int[n];
        return process(0,record,n);
    }

    public static int process(int i,int[] record,int n) {
        if(i==n) {
            return 1;
        }
        int res=0;
        for (int j = 0; j < n; j++) {
            if(isValid(record,i,j)) {
                record[i]=j;
                res+=process(i+1,record,n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record,int i,int j) {
        for (int k = 0; k < i; k++) {
            if(j==record[k]||Math.abs(record[k]-j)==Math.abs(i-k)) {
                return false;
            }
        }
        return true;
    }
}
