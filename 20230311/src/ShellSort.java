public class ShellSort {
    public static void shell(int[] array,int gap) {
        // 与直接插入排序的思想相同，不过需要分组进行直接排序
        for(int i=gap;i<array.length;i++) {
            int tmp=array[i];
            int j=i-gap;
            for(;j>=0;j-=gap) {
                if(array[j]>tmp) {
                    array[j+gap]=tmp;
                }else {
                    break;
                }
            }
            array[j+gap]=tmp;
        }
    }

    public static void shellSort(int[] array) {
        // 确定分组间隔,间隔不断减小进行预排序
        int gap=array.length;
        while (gap>1) {
            shell(array,gap);
            gap/=2;
        }
        // 再经过预排序后元素集合已经趋于有序，最后进行直接插入排序
        shell(array,1);
    }
}
