public class Test2 {
    public static int maxP(int M,int N,int[] v,int[] p) {
        if(v==null||p==null||v.length!=p.length||v.length==0||p.length==0) {
            return 0;
        }
        return process(0,M,v,p);
    }

    // index:第index个菜品的选择情况
    // rest:还剩余多少金额
    public static int process(int index,int rest,int[] v,int[] p) {
        if(rest<0) {
            return -1;
        }
        if(index==v.length) {
            return 0;
        }
        // 不要第index个菜品
        int p1=process(index+1,rest,v,p);
        // 要第index个菜品且买得起
        int p2=0;
        int next=process(index+1,rest-v[index],v,p);
        if(next!=-1) {
            p2=p[index]+next;
        }
        return Math.max(p1,p2);
    }

    public static int dp(int M,int N,int[] v,int[] p) {
        if(v==null||p==null||v.length!=p.length||v.length==0||p.length==0) {
            return 0;
        }
        int[][] dp=new int[N+1][M+1];
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 0; rest <=M ; rest++) {
                // 不要第index个菜品
                int p1=dp[index+1][rest];
                // 要第index个菜品且买得起
                int p2=0;
                int next=rest-v[index]<0?-1:dp[index+1][rest-v[index]];
                if(next!=-1) {
                    p2=p[index]+next;
                }
                dp[index][rest]=Math.max(p1,p2);
            }
        }
        return dp[0][M];
    }

    public static void main(String[] args) {
        int[] v=new int[]{2,6,2,4,5};
        int[] p=new int[]{12,16,6,14,10};
        int bag=10;
        System.out.println(maxP(bag,5, v, p));
        System.out.println(dp(bag,5, v, p));
    }
}
