public class Application {
    public static void main(String[] args) {
        AutoTest autoTest=new AutoTest();
        try {
            autoTest.test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
