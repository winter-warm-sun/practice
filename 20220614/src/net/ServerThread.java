import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Server {
    Socket socket;
    public ServerThread(Socket socket, ArrayList<Socket> sockets) {
        this.socket = socket;
        super.sockets = sockets;
    }

    public void run(){
        while (true)
            accepmessage();
    }

    public void accepmessage() {
        try {
            InputStream reader = socket.getInputStream();
            String acceptmessage=String.valueOf(socket.getPort());

            byte[] bytes = new byte[1024];
            int len;
            while ((len=reader.read(bytes) )!= -1) {
                acceptmessage="端口号为"+String.valueOf(socket.getPort())+"发送的信息: ";
                acceptmessage += new String(bytes,0,len);
                sendmessage(acceptmessage);
                bytes = new byte[1024];
                System.out.println(acceptmessage);
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException ioException) {
            }
            sockets.remove(socket);
        }
    }
    public void sendmessage(String message){
        OutputStream write1 = null;
        for (Socket socket1 : sockets) {
            try {
                write1 = socket1.getOutputStream();
                write1.write(message.getBytes());
            } catch (IOException e) {
            }
        }
    }
}
