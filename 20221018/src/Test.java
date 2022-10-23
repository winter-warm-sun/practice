public class Test {
    public static void main(String[] args) {
        String s="0011";
        int end=0;
        for (int i = 0; i < s.length(); i++) {
            end=end+(s.charAt(i)-'0')*(int)Math.pow(2,i);
        }
        System.out.println(end);
    }
}
