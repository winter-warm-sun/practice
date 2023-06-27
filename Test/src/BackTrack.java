//public class BackTrack {
//    // 表示到第t层结点
//    public static void backTrack(int t) {
//        //  到达叶节点
//        if(t>n) {
//            if(cw>bestw) {
//                for (int i = 1; i <=n ; i++) {
//                    bestx[i]=x[i];
//                }
//                bestw=cw;
//            }
//            return;
//        }
//        // 更新剩余集装箱的重量
//        r-=w[t];
//        // 搜索左子树
//        if(cw+w[t]<=c) {
//            x[t]=1;
//            cw+=w[t];
//            backTrack(t+1);
//            cw-=w[t];
//        }
//        // 搜索右子树
//        if(cw+r>bestw) {
//            x[t]=0;
//            backTrack(t+1);
//        }
//        r+=w[t]; //恢复状态
//    }
//}
