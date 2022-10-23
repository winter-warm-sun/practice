import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class UdpEchoServer {
    private DatagramSocket socket=null;
    private HashMap<String,String> dict=new HashMap<>();

    public UdpEchoServer(int port) throws SocketException {
        socket=new DatagramSocket(port);
        dict.put("hello","你好");
        dict.put("cat","小猫");
        dict.put("dog","小狗");
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        while (true) {
            DatagramPacket requestPacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);

            String request=new String(requestPacket.getData(),0, requestPacket.getLength());
            String response=process(request);
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes() ,
                    response.getBytes().length, requestPacket.getSocketAddress());
            socket.send(responsePacket);

            String log=String.format("[%s:%d] req:%s ;resp:%s",requestPacket.getAddress().toString(),requestPacket.getPort(),request,response );
            System.out.println(log);
        }
    }

    private String process(String request) {
        return dict.getOrDefault(request,"单词在词典中不存在！");
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer udpEchoServer=new UdpEchoServer(9090);
        udpEchoServer.start();
    }
}
