import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpEchoClient {
    private DatagramSocket socket=null;
    private String serverIP;
    private int serverPort;

    /**
     *
     * @param serverIP  服务器的IP
     * @param serverPort 服务器的端口
     * @throws SocketException
     */
    public UdpEchoClient(String serverIP,int serverPort) throws SocketException {
        this.serverIP=serverIP;
        this.serverPort=serverPort;
        this.socket = new DatagramSocket();
        //此处实例化Socket不用指定端口号，操作系统会随机分配空闲端口
    }

    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.print("->");
            //1.从标准输入流输入数据
            String request=scanner.nextLine();
            if(request.equals("exit")) {
                System.out.println("exit!");
                return;
            }

            //2.把字符串构造成一个UDP请求，并发送数据
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIP),serverPort);
            socket.send(requestPacket);

            //3.尝试从服务器读取响应
            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);

            //4.显示这个结果
            String response=new String(responsePacket.getData(),0, responsePacket.getLength());
            String log=String.format("req: %s ;resp: %s",request,response);
            System.out.println(log);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client=new UdpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
