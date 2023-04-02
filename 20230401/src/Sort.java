import java.util.Arrays;

public class Sort {
    public static void selectionSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        for (int i=0;i<arr.length-1;i++) {
            int minIndex=i;
            for (int j=i+1;j<arr.length;j++) {
                minIndex=arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    private static void swap(int[] arr,int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    public static void bubbleSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        for (int i=0;i<arr.length-1;i++) {
            boolean flag=true;
            for (int j=0;j<arr.length-1-i;j++) {
                if(arr[j]>arr[j+1]) {
                    swap(arr,j,j+1);
                    flag=false;
                }
            }
            if(flag) {
                break;
            }
        }
    }

    public static void insertionSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        for (int i=1;i<arr.length;i++) {
            for (int j=i-1;j>=0&&arr[j]>arr[j+1];j--) {
                swap(arr,j,j+1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr=new int[]{9,5,7,3,9,6,4};
        selectionSort(arr);
        for (int num:arr) {
            System.out.print(num+" ");
        }
    }
}
