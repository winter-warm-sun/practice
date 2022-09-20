import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpEchoServer {
    private DatagramSocket socket = null;

    /**
     * 服务器在启动的时候需要绑定一个端口号
     * 在收到数据的时候，就会根据这个端口号来决定把数据交给哪个进程
     *
     * @param port 端口号(范围为0~65535)
     * @throws SocketException
     */
    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    /**
     * 启动服务器的方法
     */
    public void start() throws IOException {
        System.out.println("服务器启动！");
        //服务器一般是一直运行着的
        while (true) {
            //1.读取请求
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            /*参数DatagramPacket是一个输出型参数，socket中读到的数据会设置到这个参数的对象中
            在构造的时候，需要指定一个缓冲区(就是一段内存空间，通常使用byte[])*/
            socket.receive(requestPacket);
            /*当前服务器不知道客户端什么时候发来请求，receive方法会阻塞等待
            如果有请求过来了，此时receive就会返回*/
            String request = new String(requestPacket.getData(), 0,
                    requestPacket.getLength());
            //把requestPacket对象里面的内容取出来，作为一个字符串

            //2.根据请求来计算响应
            String response = process(request);

            //3.把响应写回客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.getBytes().length,
                    requestPacket.getSocketAddress());
            /*此处给DatagramPacket中设置的长度，必须是“字节的个数”，
            而且在构造的时候，还需要指定这个包发给谁（其实发送给的目标就是发请求的那一方） */
            socket.send(responsePacket);

            //4.加上日志打印
            String log = String.format("[%s:%d] req: %s ;resp: %s",
                    requestPacket.getAddress().toString(), requestPacket.getPort(), request, response);
            System.out.println(log);

        }
    }

    /**
     * 根据请求来计算响应的方法（此处仅为一个回显服务器，只把客户端发来的请求返回即可）
     *
     * @param request
     * @return
     */
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server=new UdpEchoServer(9090);
        server.start();
    }
}
