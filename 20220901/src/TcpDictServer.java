import java.io.IOException;
import java.util.HashMap;

public class TcpDictServer extends TcpThreadPoolEchoServer{
    private HashMap<String,String> dict=new HashMap<>();

    public TcpDictServer(int port) throws IOException {
        super(port);

        //初始化哈希表
        dict.put("hello","你好");
        dict.put("cat","小猫");
        dict.put("dog","小狗");
    }

    //start方法不需要变动

    //processConnection方法也不需要改变

    //仅把回显功能改为翻译功能即可 （仅修改process）

    @Override
    public String process(String request) {
        return dict.getOrDefault(request,"[要查的词不存在]");
    }

    public static void main(String[] args) throws IOException {
        TcpDictServer server=new TcpDictServer(9090);
        server.start();
    }
}
