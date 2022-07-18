public class Sort {
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
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
    public static void selectSort(int[] array) {
        int minIndex=0;
        for (int i = 0; i < array.length-1; i++) {
            minIndex=i;
            for (int j = i+1; j < array.length; j++) {
                if(array[j]<array[minIndex]) {
                    minIndex=j;
                }
            }
            int tmp=array[i];
            array[i]=array[minIndex];
            array[minIndex]=tmp;
        }
    }
}
