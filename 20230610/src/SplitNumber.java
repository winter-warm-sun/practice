public class SplitNumber {
    // n为正数
    public static int ways(int n) {
        if(n<0) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        return process(1,n);
    }

    // 上一个拆出来的数是pre
    // 还剩rest需要去拆
    // 返回拆解的方法数
    public static int process(int pre, int rest) {
        if(rest==0) {
            return 1;
        }
        if(pre>rest) {
            return 0;
        }
        int ways=0;
        for (int first = pre; first <=rest ; first++) {
            ways+=process(first,rest-first);
        }
        return ways;
    }

    public static int dp1(int n) {

    }
}
