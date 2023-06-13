public class CoinsWayNoLimit {
    public static int coinsWay(int[] arr,int aim) {
        if(arr==null||arr.length==0||aim<0) {
            return 0;
        }
        return process(arr,0,aim);
    }

    public static int process(int[] arr,int index,int rest) {
        if(index==arr.length) {
            return rest==0?1:0;
        }
        int ways=0;
        for (int pages = 0; pages*arr[index] <=rest ; pages++) {
            ways+=process(arr,index+1,rest-pages*arr[index]);
        }
        return ways;
    }

    public static int dp(int[] arr,int aim) {
        if(arr==null||arr.length==0||aim<0) {
            return 0;
        }
        int N=arr.length;
        int[][] dp=new int[N+1][aim+1];
        dp[N][0]=1;
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways=0;
                for (int pages = 0; pages*arr[index] <=rest ; pages++) {
                    ways+=dp[index+1][rest-pages*arr[index]];
                }
                dp[index][rest]=ways;
            }
        }
        return dp[0][aim];
    }
}
