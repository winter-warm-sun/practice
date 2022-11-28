public class Test2 {
    public static void main(String[] args) {
        int L=5;
        int R=7;
        int mid1=L+(R-L)>>1;
        int mid2=L+((R-L)>>1);
        int a1=(R-L)>>1;
        int a2=((R-L)>>1);
        System.out.println(mid1+" "+mid2);
        System.out.println(a1+" "+a2);
    }
}
