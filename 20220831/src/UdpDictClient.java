import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpDictClient {
    private DatagramSocket socket=null;
    private String serverIP;
    private int serverPort;

    public UdpDictClient(String serverIP,int serverPort) throws SocketException {
        this.serverIP=serverIP;
        this.serverPort=serverPort;
        this.socket=new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            //1.读取输入的数据
            System.out.print("->");
            String request=scanner.next();
            if(request.equals("exit")) {
                System.out.println("goodbye!");
                return;
            }

            //2.构造请求并发送给服务器
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),
                    request.getBytes().length, InetAddress.getByName(serverIP),serverPort);
            socket.send(requestPacket);

            //3.从服务器获取响应
            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response=new String(responsePacket.getData(),0, responsePacket.getLength());

            //4.把数据显示给用户
            String log=String.format("req: %s;resp: %s",request,response);
            System.out.println(log);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpDictClient client=new UdpDictClient("127.0.0.1",9090);
        client.start();
    }
}
