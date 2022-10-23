public class Main {
    public static void main(String[] args) {
        String str="";
        System.out.println(StrToInt(str));
    }
    public static int StrToInt(String str) {
        if(str=="") {
            return 0;
        }
        StringBuilder sb=new StringBuilder();
        if(str.charAt(0)>='0'&&str.charAt(0)<='9') {
            sb.append(str.charAt(0));
        }
        for(int i=1;i<str.length();i++) {
            if(str.charAt(i)>='0'&&str.charAt(i)<='9') {
                sb.append(str.charAt(i));
            }else {
                return 0;
            }
        }
        return Integer.valueOf(sb.toString());
    }
}
