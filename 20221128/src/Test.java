import java.util.Arrays;

public class Test {
    public static void printOddTimesNum(int[] arr) {
        int eor=0;
        for (int i = 0; i < arr.length; i++) {
            eor^=arr[i];
        }
        //eor=a^b
        int rightOne=eor&(~eor+1);

        //根据最右侧的一确定的数位进行分组
        int onlyOne=0;
        for (int i = 0; i < arr.length; i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if((arr[i]&rightOne)!=0) {//该为为1进行异或
                onlyOne^=arr[i];
            }
        }
        System.out.println(onlyOne+" "+(eor^onlyOne));
    }

    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int rightOne = eor & (-eor); // 提取出最右的1


        int onlyOne = 0; // eor'
        for (int i = 0 ; i < arr.length;i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }
    public static void main(String[] args) {
        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);
    }
}
