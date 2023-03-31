import java.util.Arrays;

public class Sort {
    public static void swap(int[] arr,int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    public static void selectionSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        for (int i=0;i<arr.length-1;i++) {
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++) {
                minIndex=arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    public static void bubbleSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        // 外层循环确定趟数
        for (int i=0;i<arr.length-1;i++) {
            boolean flag=false;// 优化标记
            // 内存循环调整元素，两两比较，遍历后把最大元素放在最后，每次比较次数-1；
            for(int j=0;j<arr.length-1-i;j++) {
                if(arr[j]>arr[j+1]) {
                    swap(arr,j,j+1);
                    flag=true;
                }
            }
            if(!flag) break;
        }
    }

    public static void insertionSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        // i为0时仅一个数肯定有序
        for (int i=1;i<arr.length;i++) { // 0~i有序
            for (int j=i-1;j>=0&&arr[j]>arr[j+1];j--) {
                swap(arr,j,j+1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr=new int[]{6,8,5,3,9};
        bubbleSort(arr);
        for (int a:arr) {
            System.out.print(a+" ");
        }
    }
}
