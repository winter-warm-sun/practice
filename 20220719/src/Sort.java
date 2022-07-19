public class Sort {
    public static void insertSort(int[] array) {
        //只有一个元素时肯定是有序的，所以遍历从第二个元素开始
        for (int i = 1; i < array.length; i++) {
            int tmp=array[i];
            int j=i-1;
            //将该位置的值依次往前比较，如果不符合升序则调整，符合则调整结束
            for (; j >=0 ; j--) {
                if(array[j]>tmp) {
                    array[j+1]=array[j];
                }else {
                    break;
                }
            }
            //j下标的下一个位置就是tmp应该在的位置
            array[j+1]=tmp;
        }
    }
    public static void shell(int[] array,int gap) {
        //与直接插入排序的思想相同，不过是需要分组进行直接排序
        for (int i = gap; i < array.length; i++) {
            int tmp=array[i];
            int j=i-gap;
            for (; j >=0 ; j--) {
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
        //确定分组间隔，间隔不断减小进行预排序
        int gap=array.length;
        while (gap>1) {
            shell(array,gap);
            gap/=2;
        }
        //再经过预排序后元素集合已经趋于有序，最后进行直接插入排序
        shell(array,1);
    }
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            int minIndex=i;
            for (int j = i+1; j <array.length ; j++) {
                if(array[j]<array[minIndex]) {
                    minIndex=j;
                }
            }
            swap(array,minIndex,i);
        }
    }
    /**
     * 交换函数
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array,int i,int j) {
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }

    public static void selectSort2(int[] array) {
        int left=0;
        int right=array.length-1;
        while (left<right) {
            int minIndex=left;
            int maxIndex=left;
            for (int i = left+1; i <=right ; i++) {
                if(array[i]<array[minIndex]) {
                    minIndex=i;
                }
                if(array[i]>array[maxIndex]) {
                    maxIndex=i;
                }
            }
            swap(array,left,minIndex);
            //修正max的下标
            if(left==maxIndex) {
                maxIndex=minIndex;
            }
            swap(array,right,maxIndex);
            left++;
            right--;
        }
    }
}
