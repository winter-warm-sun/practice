import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpThreadEchoServer {
    private ServerSocket listenSocket=null;

    public TcpThreadEchoServer(int port) throws IOException {
        this.listenSocket =new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        while(true) {
            //主线程中循环调用accept，就能保证在一次调用完成后，立刻进行再次调用
            Socket clientSocket=listenSocket.accept();

            //每次获取到一个连接，就创建一个线程来给客户端提供服务
            Thread t=new Thread(()->{
                try {
                    processConnection(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }

    private void processConnection(Socket clientSocket) throws IOException {
        String log=String.format("[%s:%d] 客户端上线！",
                clientSocket.getInetAddress().toString(),clientSocket.getPort());
        System.out.println(log);
        try(InputStream inputStream= clientSocket.getInputStream();
            OutputStream outputStream= clientSocket.getOutputStream()) {
            while(true) {
                //1.读取并请求解析
                Scanner scanner=new Scanner(inputStream);
                if(!scanner.hasNext()) {
                    log=String.format("[%s:%d] 客户端下线！",
                            clientSocket.getInetAddress().toString(),clientSocket.getPort());
                    System.out.println(log);
                    break;
                }
                String request= scanner.next();

                //2.根据请求计算响应
                String response=process(request);

                //3.把响应写回客户端
                PrintWriter printWriter=new PrintWriter(outputStream);
                printWriter.println(response);
                printWriter.flush();

                log=String.format("[%s:%d] req: %s;resp: %s",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
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
        TcpThreadEchoServer server=new TcpThreadEchoServer(9090);
        server.start();
    }
}
