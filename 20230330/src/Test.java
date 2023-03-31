public class Test {
    public static boolean exist(int[] sortedArr,int num) {
        if(sortedArr==null||sortedArr.length==0) {
            return false;
        }
        int L=0;
        int R=sortedArr.length-1;
        int mid=0;
        while (L<R) {
            mid=L+((R-L)>>1);
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

    public static int nearestLeft(int[] arr,int value) {
        int L=0;
        int R=arr.length-1;
        int index=-1;
        while (L<=R) {
            int mid=L+((R-L)>>1);
            if(arr[mid]>=value) {
                index=mid;
                R=mid-1;
            }else {
                L=mid+1;
            }
        }
        return index;
    }

    public static int nearestRight(int[] arr,int value) {
        int L=0;
        int R=arr.length-1;
        int index=-1;
        while (L<=R) {
            int mid=L+((R-L)>>1);
            if(arr[mid]<=value) {
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
        int mid=0;
        while (L<R) {
            mid=L+((R-L)>>1);
            if(arr[mid]>arr[mid-1]) {
                R=mid-1;
            }else if(arr[mid]<arr[mid+1]) {
                L=mid+1;
            }else {
                return mid;
            }
        }
        return L;
    }
}
