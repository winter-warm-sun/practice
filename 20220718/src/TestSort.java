import java.util.ArrayList;
import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        int[] array={10,5,8,1,6};
        Sort.selectSort(array);
        System.out.println(Arrays.toString(array));
    }
}
