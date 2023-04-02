public class Exit {
    public static boolean exist(int[] sortArr,int num) {
        if(sortArr==null||sortArr.length==0) {
            return false;
        }
        int L=0;
        int R=sortArr.length-1;
        while (L<R) {
            int mid=L+((R-L)>>1);
            if(sortArr[mid]>num) {
                R=mid-1;
            }else if(sortArr[mid]<num) {
                L=mid+1;
            }else {
                return true;
            }
        }
        return sortArr[L]==num;
    }

    public static int nearestLeft(int[] sortArr,int num) {
        int L=0;
        int R=sortArr.length-1;
        int index=-1;
        while (L<=R) {
            int mid=L+((R-L)>>1);
            if(sortArr[mid]>=num) {
                index=mid;
                R=mid-1;
            }else {
                L=mid+1;
            }
        }
        return index;
    }

    public static int nearestRight(int[] sortArr,int num) {
        int L=0;
        int R=sortArr.length-1;
        int index=-1;
        while (L<=R) {
            int mid=L+((R-L)>>1);
            if(sortArr[mid]<=num) {
                index=mid;
                L=mid+1;
            }else {
                R=mid-1;
            }
        }
        return index;
    }

    public static int getLessIndex(int[] arr) {
        if(arr==null||arr.length==0) {
            return -1;
        }
        if(arr.length==1||arr[0]<arr[1]) {
            return 0;
        }
        if(arr[arr.length-1]<arr[arr.length-2]) {
            return arr.length-1;
        }
        int L=1;
        int R=arr.length-2;
        while (L<R) {
            int mid=L+((R-L)>>1);
            if(arr[mid-1]<arr[mid]) {
                R=mid-1;
            }else if(arr[mid]>arr[mid+1]) {
                L=mid+1;
            }else {
                return mid;
            }
        }
        return L;
    }
}
