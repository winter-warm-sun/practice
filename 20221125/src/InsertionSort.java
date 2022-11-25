import java.util.Arrays;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        //i为0仅一个数时肯定有序
        for(int i=1;i<arr.length;i++) {//0~i 做到有序
            for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--) {
                swap(arr,j,j+1);
            }
        }
    }
    public static void swap(int[] arr,int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize,int maxValue) {
        int[] arr=new int[(int)((maxSize+1)*Math.random())];
        //长度随机
        for(int i=0;i<arr.length;i++) {
            arr[i]=(int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if(arr==null) {
            return null;
        }
        int[] res=new int[arr.length];
        for(int i=0;i<arr.length;i++) {
            res[i]=arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1,int[] arr2) {
        if((arr1==null&&arr2!=null)||(arr1!=null&&arr2==null)) {
            return false;
        }
        if(arr1==null&&arr2==null) {
            return true;
        }
        if(arr1.length!=arr2.length) {
            return false;
        }
        for(int i=0;i<arr1.length;i++) {
            if(arr1[i]!=arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if(arr==null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime=500000;
        int maxSize=10;//随机数组长度0~100
        int maxValue=100;//随机值：-100~100
        boolean succeed=true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1=generateRandomArray(maxSize,maxValue);
            int[] arr2=copyArray(arr1);
            insertionSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2)) {
                System.out.println("Fucking fucked!");
                succeed=false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        if(succeed) {
            System.out.println("Nice!");
            int[] arr1=generateRandomArray(maxSize,maxValue);
            int[] arr2=copyArray(arr1);
            insertionSort(arr1);
            comparator(arr2);
            printArray(arr1);
            printArray(arr2);
        }
    }
}
