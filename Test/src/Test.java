public class Test {
    public static int maxValue(int[] w,int[] v,int bag) {
        if(w==null||v==null||w.length!=v.length||w.length==0||v.length==0) {
            return 0;
        }
        return process(w,v,0,bag);
    }

    // index:第index个物品
    // rest:背包的剩余容量
    public static int process(int[] w,int[] v,int index,int rest) {
        if(rest<0) {
            return -1;
        }
        if(index==w.length) {
            return 0;
        }
        // 不要第index个物品
        int p1=process(w,v,index+1,rest);
        int p2=0;
        int next=process(w,v,index+1,rest-w[index]);
        // 要第index个物品，且可以装下
        if(next!=-1) {
            p2=v[index]+next;
        }
        return Math.max(p1,p2);
    }

    public static int dp(int[] w,int[] v,int bag) {
        if(w==null||v==null||w.length!=v.length||w.length==0||v.length==0) {
            return 0;
        }
        int N=w.length;
        int[][] dp=new int[N+1][bag+1];
        for (int index = N-1; index >=0 ; index--) {
            for (int rest = 0; rest <=bag ; rest++) {
                // 不要第index个物品
                int p1=dp[index+1][rest];
                int p2=0;
                int next=rest-w[index]<0?-1:dp[index+1][rest-w[index]];
                // 要第index个物品，且可以装下
                if(next!=-1) {
                    p2=v[index]+next;
                }
                dp[index][rest]=Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] w=new int[]{2,6,2,4,5};
        int[] v=new int[]{12,16,6,14,10};
        int bag=10;
        System.out.println(dp(w, v, bag));
    }
}
