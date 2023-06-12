public class SplitSumClosedSizeHalf {
    public static int right(int[] arr) {
        if(arr==null||arr.length<2) {
            return 0;
        }
        int sum=0;
        for (int num:arr) {
            sum+=num;
        }
        if((arr.length&1)==0) {
            return process(arr,0,arr.length/2,sum/2);
        }else {
            return Math.max(process(arr,0,arr.length/2,sum/2),process(arr,0,arr.length/2+1,sum/2));
        }
    }
    // arr[i...]自由选择，挑选的个数一定是picks个，累加和<=rest，离rest最接近的返回
    public static int process(int[] arr, int i, int picks, int rest) {
        if(i==arr.length) {
            return picks==0?0:-1;  // 不合法用-1标记
        }else {
            // 不用arr[i]
            int p1=process(arr, i+1, picks, rest);
            // 用arr[i]
            int p2=-1;
            int next=-1;
            if(arr[i]<=rest) {
                next=process(arr,i+1,picks-1,rest-arr[i]);
            }
            // 如果下一层有效
            if(next!=-1) {
                p2=arr[i]+next;
            }
            return Math.max(p1,p2);
        }
    }

    public static int dp(int[] arr) {
        if(arr==null||arr.length<2) {
            return 0;
        }
        int sum=0;
        for (int num:arr) {
            sum+=num;
        }
        sum/=2;
        int N=arr.length;
        int M=(N+1)/2;// 向上取整
        int[][][] dp=new int[N+1][M+1][sum+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int rest = 0; rest <=sum ; rest++) {
            dp[N][0][rest]=0;
        }
        for (int i = N-1; i >=0 ; i--) {
            for (int picks = 0; picks <=M ; picks++) {
                for (int rest = 0; rest <=sum ; rest++) {
                    // 不用arr[i]
                    int p1=dp[i+1][picks][rest];
                    // 用arr[i]
                    int p2=-1;
                    int next=-1;
                    if(arr[i]<=rest) {
                        next=dp[i+1][picks-1][rest-arr[i]];
                    }
                    // 如果下一层有效
                    if(next!=-1) {
                        p2=arr[i]+next;
                    }
                    dp[i][picks][rest]= Math.max(p1,p2);
                }
            }
        }
        if((arr.length&1)==0) {
            return dp[0][arr.length/2][sum];
        }else {
            return Math.max(dp[0][arr.length/2][sum],dp[0][arr.length/2+1][sum]);
        }
    }


    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2 ) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
