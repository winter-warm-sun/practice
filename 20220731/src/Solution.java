public class Solution {
    private  int midNumIndex(int[] array,int left,int right) {
        int mid=(left+right)/2;
        int a=array[left]>array[right]?array[left]:array[right];
        a=a>array[mid]?array[mid]:a;
        if(a==array[left]) {
            return left;
        }else if(a==array[mid]) {
            return mid;
        }else {
            return right;
        }
    }
    private  void quick(int[] array,int left,int right) {
        if(left>=right) {
            return;
        }
        //三数取中：解决递归深度问题 基本上 有了三数取中  你的待排序序列 基本上每次都是二分N*logn
        int index = midNumIndex(array,left,right);
        swap(array,left,index);
        int pivot=partitionHoare(array,left,right);
        quick(array,left,pivot-1);
        quick(array,pivot+1,right);
    }
    private int partitionHoare(int[] array,int start,int end) {
        int i=start;//先存储好start下标，用于最后的交换
        int key=array[start];
        while (start<end) {
            //为什么取等号？为什么后指针先走？在下边分析中解释
            while (start<end&&array[end]>=key) {
                end--;
            }
            while (start<end&&array[start]<=key) {
                start++;
            }
            swap(array,start,end);
        }
        swap(array,start,i);
        return start;
    }

    public  void swap(int[] array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }
    public int[] sortArray(int[] nums) {
        quick(nums,0,nums.length-1);
        return nums;
    }
}