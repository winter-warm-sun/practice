import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    private String serverIp;
    private int serverPort;
    private Socket socket=null;

    public TcpEchoClient(String serverIp, int serverPort) throws IOException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socket=new Socket(serverIp,serverPort);
    }

    public void start() {
        Scanner scanner=new Scanner(System.in);
        try (InputStream inputStream= socket.getInputStream();
             OutputStream outputStream= socket.getOutputStream()){
            while (true) {
                System.out.print(">");
                String request=scanner.next();
                if(request.equals("exit")) {
                    break;
                }
                PrintWriter printWriter=new PrintWriter(outputStream);
                printWriter.println(request);
                printWriter.flush();

                Scanner respScanner=new Scanner(inputStream);
                String response=respScanner.next();

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
