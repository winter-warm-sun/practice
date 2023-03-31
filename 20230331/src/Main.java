public class Main {
    public static void printOddTimesNum(int[] arr) {
        int eor=0;
        for (int i=0;i<arr.length;i++) {
            eor^=arr[i];
        }
        int rightOne=eor&(~eor+1);
        int onlyOne=0;
        for (int i=0;i<arr.length;i++) {
            if((arr[i]&rightOne)!=0) {
                onlyOne^=arr[i];
            }
        }
        System.out.println(onlyOne+" "+(onlyOne^eor));
    }

    public static int km(int[] arr,int k,int m) {
        int[] count=new int[32];
        for (int num:arr) {
            for (int i=0;i<32;i++) {
                count[i]+=(num>>i)&1;
            }
        }
        int ans=0;
        for (int i=0;i<32;i++) {
            count[i]%=m;
            if(count[i]!=0) {
                ans|=1<<i;
            }
        }
        return ans;
    }
}
