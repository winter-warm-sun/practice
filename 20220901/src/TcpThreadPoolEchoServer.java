import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpThreadPoolEchoServer {
    private ServerSocket listenSocket=null;

    public TcpThreadPoolEchoServer(int port) throws IOException {
        this.listenSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        ExecutorService executorService= Executors.newCachedThreadPool();
        while (true) {
            Socket clientSocket=listenSocket.accept();
            //使用线程池来处理当前的processConnection
            executorService.submit(()->{
                try {
                    processConnection(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            
        }
    }

    public void processConnection(Socket clientSocket) throws IOException {
        String log = String.format("[%s:%d] 客户端上线!", clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        System.out.println(log);
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            while (true) {
                // 1. 读取请求并解析
                Scanner scanner = new Scanner(inputStream);
                if (!scanner.hasNext()) {
                    log = String.format("[%s:%d] 客户端下线!", clientSocket.getInetAddress().toString(),
                            clientSocket.getPort());
                    System.out.println(log);
                    break;
                }
                String request = scanner.next();
                // 2. 根据请求计算响应
                String response = process(request);
                // 3. 把响应写回到客户端
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(response);
                printWriter.flush();

                log = String.format("[%s:%d] req: %s; resp: %s", clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(), request, response);
                System.out.println(log);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }
    }

    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpThreadPoolEchoServer server=new TcpThreadPoolEchoServer(9090);
        server.start();
    }
}
