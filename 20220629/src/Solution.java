import java.util.Arrays;

class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int []tem= new int[m+n];
        int i=0,j=0,end=0;
        while (i<m&&j<n) {
           tem[end++]=(nums1[i]<nums2[j]?nums1[i++]:nums2[j++]);
        }
        if(i==m) {
            System.arraycopy(nums2,j,tem,end,n-j);
        }
        if(j==n) {
            System.arraycopy(nums1,i,tem,end,m-i);
        }
//        nums1=Arrays.copyOf(tem,tem.length);
        for (int k = 0; k < tem.length; k++) {
            nums1[k]=tem[k];
        }
    }

    public static void main(String[] args) {
        int []nums1={1,2,3,0,0,0};
        int[]nums2={2,5,6};
        merge(nums1,3,nums2,3);
    }
}