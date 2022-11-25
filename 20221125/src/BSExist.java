public class BSExist {
    public static boolean exist(int[] sortedArr,int num) {
        if(sortedArr==null||sortedArr.length==0) {
            return false;
        }
        int L=0;
        int R=sortedArr.length-1;
        int mid=0;
        while (L<R) {//L..R至少有两个数的时候,退出循环的时候只剩一个数
            mid=L+(R-L)>>1;
            if(sortedArr[mid]==num) {
                return true;
            }else if(sortedArr[mid]>num) {
                R=mid-1;
            }else {
                L=mid+1;
            }
        }
        return sortedArr[L]==num;
    }
}
