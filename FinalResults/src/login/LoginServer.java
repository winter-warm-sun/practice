package login;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 *
 * 多线程
 * 注意：服务器端和客户端包名要相同，所用的User类也要相同
 * @author Administrator
 *
 */
public class LoginServer {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;

        try {
            //创建套接字
            server = new ServerSocket(6688);

            //实现多线程，多次接受客户端请求
            while (true) {
                client = server.accept();
                System.out.println(client.getInetAddress().getHostName() + "连接成功");
                ServerRunnable sr = new ServerRunnable(client);
                new Thread(sr).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}