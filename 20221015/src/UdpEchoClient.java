import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpEchoClient {
    private DatagramSocket socket=null;
    private String serverIP;
    private int serverPort;

    public UdpEchoClient(String serverIP, int serverPort) throws SocketException {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.socket=new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.print("->");
            String request=scanner.nextLine();
            if(request.equals("exit")) {
                System.out.println("exit!");
                return;
            }

            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIP),serverPort);
            socket.send(requestPacket);

            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);

            String response=new String(responsePacket.getData(),0, responsePacket.getLength());
            String log=String.format("req:%s ;resp:%s",request,response);
            System.out.println(log);
        }

    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client=new UdpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
