public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if(arr==null||arr.length<2) {
            return;
        }
        //外层循环确定趟数
        for(int i=0;i< arr.length-1;i++) {
            boolean flag=false;//优化标记
            //内层循环调整元素，两两比较，遍历后把最大元素放在最后，每次比较次数-1
            for(int j=0;j< arr.length-1-i;j++) {
                if(arr[j]>arr[j+1]) {
                    swap(arr,j,j+1);
                    flag=true;
                }
            }
            if(!flag) break;
        }
    }

    public static void swap(int[] arr,int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
