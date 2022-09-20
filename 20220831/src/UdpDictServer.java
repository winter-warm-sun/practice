import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

public class UdpDictServer {
    private DatagramSocket socket=null;

    private HashMap<String,String> dict=new HashMap<>();
    //词典  哈希表 一一对应

    public UdpDictServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
        //初始化词典内容
        dict.put("hello","你好");
        dict.put("cat","小猫");
        dict.put("dog","小狗");
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        while(true) {
            //1.读取请求并解析
            DatagramPacket requestPacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request=new String(requestPacket.getData(),0, requestPacket.getLength());

            //2.根据请求计算响应
            String response=process(request);

            //3.把响应写回到客户端
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),
                    response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);

            //4.日志打印
            String log=String.format("[%s:%d] req: %s;resp: %s",requestPacket.getAddress(),
                    requestPacket.getPort(),request,response);
            System.out.println(log);
        }
    }

    private String process(String request) {
        return dict.getOrDefault(request,"[单词在词典中不存在！]");
    }

    public static void main(String[] args) throws IOException {
        UdpDictServer server=new UdpDictServer(9090);
        server.start();
    }
}
