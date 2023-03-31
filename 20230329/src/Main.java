import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        String salt= UUID.randomUUID().toString().replace("-","");
        System.out.println(salt);
    }
}
