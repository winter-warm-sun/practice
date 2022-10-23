import java.util.ArrayList;
import java.util.List;

class Solution {
    static private class Pair {
        int val,id;
        Pair(int val,int id) {
            this.val=val;
            this.id=id;
        }
    }
    static private int[] count;

    public static void main(String[] args) {
        int[] nums=new int[]{5,2,6,1};
        countSmaller(nums);
    }
    public static List<Integer> countSmaller(int[] nums) {
        int n=nums.length;
        count=new int[n];
        Pair[] arr=new Pair[n];
        for(int i=0;i<n;i++) {
            arr[i]=new Pair(nums[i],i);
        }
        mergeSortFunc(arr,0,nums.length-1);

        List<Integer> res=new ArrayList<>();
        for(int c:count) res.add(c);
        return res;
    }

    static private  void mergeSortFunc(Pair[] array,int left,int right) {
        if(left>=right) {
            return;
        }
        int mid=(left+right)/2;
        //分解左边
        mergeSortFunc(array,left,mid);
        //分解右边
        mergeSortFunc(array,mid+1,right);
        //进行合并
        merge(array,left,right,mid);
    }
    static private void merge(Pair[] array,int start,int end,int midIndex) {
        Pair[] tmpArr=new Pair[end-start+1];//开辟临时数组用于存储排好序的数组
        int k=0;
        int s1=start;//两个区间段
        int s2=midIndex+1;
        while (s1<=midIndex&&s2<=end) {//保证两个区间段都有数
            if(array[s1].val<=array[s2].val) {
                tmpArr[k++]=array[s1++];
            }else {
                tmpArr[k++]=array[s2++];
                count[array[s2-1].id]+=s2-midIndex;
            }
        }
        //当两个归并段中有一段没数据后，把另一段剩余数据全部拷贝到tmpArr数组中去
        while (s1<=midIndex) {
            tmpArr[k++]=array[s1++];
        }
        if(s2<=end) {
            while (s2<=end) {
                tmpArr[k++]=array[s2++];
            }
            count[array[s2-1].id]+=s2-midIndex;
        }
        //把排好序的数字拷贝回原数组
        for (int i = 0; i < k; i++) {
            array[i+start]=tmpArr[i];
        }
    }
}