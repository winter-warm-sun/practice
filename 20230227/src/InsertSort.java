public class InsertSort {
    public static void insertSort(int[] array) {
        for (int i=1;i<array.length;i++) {
            int tmp=array[i];
            int j=i-1;
            for (;j>=0;j--) {
                if(array[j]>tmp) {
                    array[j+1]=array[j];
                }else {
                    break;
                }
            }
            array[j+1]=tmp;
        }
    }

    public static void shell(int[] array,int gap) {
        for (int i=gap;i<array.length;i++) {
            int tmp=array[i];
            int j=i-gap;
            for (;j>=0;j-=gap) {
                if(array[j]>tmp) {
                    array[j+gap]=array[j];
                }else {
                    break;
                }
            }
            array[j+gap]=tmp;
        }
    }

    public static void shellSort(int[] array) {
        int gap=array.length;
        while (gap>1) {
            shell(array,gap);
            gap/=2;
        }
        shell(array,1);
    }

    public static void swap(int[] array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    public static void selectSort(int[] array) {
        for (int i=0;i<array.length-1;i++) {
            int minIndex=i;
            for (int j=i+1;j<array.length;j++) {
                if(array[j]<array[minIndex]) {
                    minIndex=j;
                }
            }
            swap(array,minIndex,i);
        }
    }

    public static void selectSort2(int[] array) {
        int left=0;
        int right=array.length-1;
        while (left<right) {
            int minIndex=left;
            int maxIndex=left;
            for (int i=left+1;i<=right;i++) {
                if(array[i]<array[minIndex]) {
                    minIndex=i;
                }
                if(array[i]>array[maxIndex]) {
                    maxIndex=i;
                }
            }
            swap(array,left,minIndex);
            // 修正max的下标
            if(left==maxIndex) {
                maxIndex=minIndex;
            }
            swap(array,right,maxIndex);
            left++;
            right--;
        }
    }

    public static void bubbleSort(int[] array) {
        // 外层循环确定趟数
        for (int i=0;i<array.length-1;i++) {
            boolean flag=false;
            // 内层循环调整元素，遍历后把最大元素放在最后，所以比较次数每次-1
            for (int j=0;j<array.length-1-i;j++) {
                if(array[j]>array[j+1]) {
                    swap(array,j,j+1);
                    flag=true;
                }
            }
            if(!flag) break;
        }
    }
}
