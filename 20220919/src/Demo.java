public class Demo {
    public static void main(String[] args) {
        int a=0,b=1,c=0;
        while(c<10) {
            c=a+b;
            a=b;
            b=c;
        }
        System.out.println(c);
        System.out.println(a);
    }
}
