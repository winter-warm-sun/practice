class SingletonLazy {
    private SingletonLazy() {}
    private static SingletonLazy instance=null;

    public static SingletonLazy getInstance() {
        if(instance==null) {
            instance=new SingletonLazy();
        }
        return instance;
    }
}
public class Demo1 {
}
