public class NQueens {
    public static int num1(int n) {
        if(n<1) {
            return 0;
        }
        int[] record=new int[n];
        return process(0,record,n);
    }

    // 当前来到第i行，一共是0~N-1行
    // 在i行上放皇后，所有列都尝试
    // 必须要保证跟之前所有的皇后都不冲突
    // int[] record   record[x]=y   第x行的皇后  放在了y列上
    // 返回：不关心i以上发生了什么，i... 后续有多少合法的方法数
    public static int process(int i,int[] record,int n) {
        if (i==n) {
            return 1;
        }
        int res=0;
        // i行的皇后，放在第j列
        for (int j = 0; j < n; j++) {
            if(isValid(record,i,j)) {
                record[i]=j;
                res+=process(i+1,record,n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record,int i,int j) {
        // 0...i-1
        for (int k = 0; k < i; k++) {
            // 判断上方或者左斜方或者右斜方是否有其他皇后
            if(j==record[k]||Math.abs(record[k]-j)==Math.abs(i-k)) {
                return false;
            }
        }
        return true;
    }
}
