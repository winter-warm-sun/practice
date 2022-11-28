

import java.util.Arrays;

public class BSExit {

    public static boolean exist(int[] sortedArr,int num) {
        if(sortedArr==null||sortedArr.length==0) {
            return false;
        }
        int L=0;
        int R=sortedArr.length-1;
        int mid=0;
        while (L<R) {//L..R至少有两个数的时候,退出循环的时候只剩一个数
            mid=L+((R-L)>>1);//(相当于(L+R)/2,为了防止溢出此处做优化)
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

    // for test
    public static boolean test(int[] sortedArr, int num) {
        for(int cur : sortedArr) {
            if(cur == num) {
                return true;
            }
        }
        return false;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}