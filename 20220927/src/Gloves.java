import java.util.*;

public class Gloves {
    public static int findMinimum(int n, int[] left, int[] right) {
        // write code here
        int sum=0;
        int leftSum=0;
        int rightSum=0;
        int leftMin=Integer.MAX_VALUE;
        int rightMin=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            if(left[i]*right[i]==0) {
                sum+=left[i]+right[i];
            }else {
                leftSum+=left[i];
                if(leftMin>left[i]) {
                    leftMin=left[i];
                }
                rightSum+=right[i];
                if(rightMin>right[i]) {
                    rightMin=right[i];
                }
            }
        }
        return sum+Math.min(leftSum-leftMin+1,rightSum-rightMin+1)+1;
    }

    public static void main(String[] args) {
        int[] left=new int[]{0,7,1,6};
        int[] right=new int[]{1,5,0,6};
        System.out.println(findMinimum(4,left,right));
    }
}