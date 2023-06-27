class Solution {
    public static void main(String[] args) {
        int[] nums1=new int[]{0,1,1,1,1};
        int[] nums2=new int[]{1,0,1,0,1};
        System.out.println(findLength(nums1,nums2));
    }

    public static int findLength(int[] nums1, int[] nums2) {
        int N=nums1.length;
        int M=nums2.length;
        int res=0;
        for(int i=0;i<N;i++) {
            int cur=i;
            int len=0;
            for(int j=0;j<M;j++) {
                while(i<N&&j<M&&nums1[i]!=nums2[j]) {
                    j++;
                }
                while(i<N&&j<M&&nums1[i]==nums2[j]) {
                    i++;
                    j++;
                    len++;
                }
            }
            i=cur;
            res=Math.max(res,len);
        }
        return res;
    }
}