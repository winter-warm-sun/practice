import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpEchoServer {
    private ServerSocket listenSocket=null;

    public TcpEchoServer(int port) throws IOException {
        this.listenSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        ExecutorService service= Executors.newCachedThreadPool();
        while (true) {
            Socket clientSocket=listenSocket.accept();
            service.submit(()-> {
                try {
                    processConnection(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        }
    }

    private void processConnection(Socket clientSocket) throws IOException {
        String log=String.format("[%s:%d] 客户端上线！",
                clientSocket.getInetAddress().toString(),clientSocket.getPort());
        System.out.println(log);
        try (InputStream inputStream=clientSocket.getInputStream();
             OutputStream outputStream=clientSocket.getOutputStream()){
            while (true) {
                Scanner scanner=new Scanner(inputStream);
                if(!scanner.hasNext()) {
                    log=String.format("[%s:%d] 客户端下线！",
                            clientSocket.getInetAddress().toString(),clientSocket.getPort());
                    System.out.println(log);
                    break;
                }
                String request=scanner.next();
                String response=process(request);

                PrintWriter writer=new PrintWriter(outputStream);
                writer.println(response);
                writer.flush();

                log=String.format("[%s:%d] req:%s;resp:%s",
                        clientSocket.getInetAddress().toString(),clientSocket.getPort(),request,response);
                System.out.println(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer  server=new TcpEchoServer(9090);
        server.start();
    }
}
