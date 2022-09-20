import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    private String serverIP;
    private int serverPort;
    private Socket socket=null;

    public TcpEchoClient(String serverIP,int serverPort) throws IOException {
        this.serverIP = serverIP;
        this.serverPort=serverPort;
        //TCP网络编程中，让socket创建的同时，就和服务器尝试建立连接
        this.socket=new Socket(serverIP,serverPort);
    }

    public void start() {
        Scanner scanner=new Scanner(System.in);
        try (InputStream inputStream= socket.getInputStream();
             OutputStream outputStream= socket.getOutputStream()){
            while(true) {
                //1.从键盘上读取用户输入的内容
                System.out.print("->");
                String request=scanner.next();
                if(request.equals("exit")) {
                    break;
                }

                //2.把读取的内容构造成请求，发送给服务器
                PrintWriter printWriter=new PrintWriter(outputStream);
                printWriter.println(request);
                printWriter.flush();

                //3.从服务器读取响应并解析
                Scanner respScanner=new Scanner(inputStream);
                String response=respScanner.next();

                //4.把结果显示到界面上
                String log=String.format("req: %s;resp: %s",request,response);
                System.out.println(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client=new TcpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
