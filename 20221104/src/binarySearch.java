import java.util.Arrays;

public class binarySearch {
    public static void main(String[] args) {
        int[] array=new int[]{7,5,9,4,2,14};
        Arrays.sort(array);
        System.out.println(search(array,4));
    }
    public static int search(int[] array,int value) {
        int begin=0;
        int end=array.length-1;
        while (begin<=end) {
            int mid=begin+(end-begin)/2;
            if(array[mid]>value) {
                end=mid-1;
            }else if(array[mid]<value) {
                begin=mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
