import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoServer {
    private ServerSocket listenSocket=null;

    public TcpEchoServer(int port) throws IOException {
        this.listenSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        while(true) {
            Socket clientSocket=listenSocket.accept();
            processConnection(clientSocket);
        }
    }

    /**
     * 处理一个连接。在这个连接中可能会涉及到客户端和服务器的多次交互
     * @param clientSocket
     */
    private void processConnection(Socket clientSocket) throws IOException {

        String log=String.format("[%s:%d] 客户端上线！",
                clientSocket.getInetAddress().toString(),clientSocket.getPort());
        System.out.println(log);
        try(InputStream inputStream= clientSocket.getInputStream();
            OutputStream outputStream= clientSocket.getOutputStream()) {
            while (true) {
                //1.读取请求并解析
                Scanner scanner=new Scanner(inputStream);

                if(!scanner.hasNext()) {
                    log=String.format("[%s:%d] 客户端下线！",
                            clientSocket.getInetAddress().toString(),clientSocket.getPort());
                    System.out.println(log);
                    //打印下线日志
                    break;
                }
                String request=scanner.next();

                //2.根据请求计算响应
                String response=process(request);

                //3.把响应写回客户端
                PrintWriter writer=new PrintWriter(outputStream);
                writer.println(response);
                writer.flush();

                log=String.format("[%s:%d] req: %s;resp: %s",
                        clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
                System.out.println(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*
            当前的clientSocket的生命周期不是跟随整个程序，而是和连接相关
            因此就需要每个连接结束时，都要进行关闭
            否则随着连接的增多，这个socket文件就可能出现资源泄露的情况
             */
            clientSocket.close();
        }
    }

    /**
     * 回显服务器：客户端发什么就返回什么
     * @param request
     * @return
     */
    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server=new TcpEchoServer(9090);
        server.start();
    }
}
