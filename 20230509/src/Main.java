import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static int numRescue(int[] people,int limit) {
        Arrays.sort(people);
        int left=0,right=people.length-1;
        int count=0;
        while (left<=right) {
            if(people[left]+people[right]<=limit){
                left++;
            }
            right--;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] people=new int[]{1,2};
        int limit=3;
        System.out.println(numRescue(people, limit));
    }
}
