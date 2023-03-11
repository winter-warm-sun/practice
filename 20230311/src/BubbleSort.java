public class BubbleSort {
    public static void bubbleSort(int[] array) {
        //外层循环确定趟数
        for(int i=0;i<array.length-1;i++) {
            boolean flag=false;
            // 内层循环调整元素，遍历后把最大元素放在最后，所以比较次数每次减1
            for (int j=0;j<array.length-1-i;j++) {
                if(array[j]>array[j+1]) {
                    swap(array,j,j+1);
                    flag=true;
                }
            }
            if(!flag) {
                break;
            }
        }
    }

    public static void swap(int[] array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }
}
