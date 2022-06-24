package Game;

import Main.TankClient;
import Main.TankServer;
import Message.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 代表网络连接的客户端类
 */
public class NetClient {
	TankClient tc;

	private int udpPort;

	String IP; // server IP

	DatagramSocket ds = null;
	
	/**
	 * 根据场所构建网络客户端
	 * @param tc 游戏场所
	 */
	public NetClient(TankClient tc) {
		this.tc = tc;

	}
	
	/**
	 * 连接服务器
	 * @param IP 服务器IP
	 * @param port 服务器端口
	 */
	public void connect(String IP, int port) {

		this.IP = IP;

		try {
			ds = new DatagramSocket(udpPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		Socket s = null;
		try {
			s = new Socket(IP, port);
			//使用数据流来传输数据
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpPort);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt();
			tc.myTank.id = id;
			//通过端口号的奇偶性来判断坦克的好坏
			if (id % 2 == 0)
				tc.myTank.good = false;
			else
				tc.myTank.good = true;

			System.out.println("Connected to server! and server give me a ID:"
					+ id);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
					s = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//每次完成连接后，Client端都会传输给Server端坦克新诞生的消息，
		TankNewMsg msg = new TankNewMsg(tc.myTank);
		send(msg);
		//启动线程
		new Thread(new UDPRecvThread()).start();
	}
	
	/**
	 * 发送消息
	 * @param msg 待发送的消息
	 */
	public void send(Msg msg) {
		//send方法单独包装，完成信息的传输
		//此处体现多态的好处，不同类别的消息去实现同一个接口，调用send方法时发生向上转型，通过多态来实现不同类别方法的传输
		msg.send(ds, IP, TankServer.UDP_PORT);
	}

	private class UDPRecvThread implements Runnable {
		//Client端接受信息线程，接受从Server端发送过来的UDP包
		byte[] buf = new byte[1024];
		//把从Server端传过来的包读取到数组中
		public void run() {

			while (ds != null) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(dp);
					parse(dp);//解析传输的数据包
					System.out.println("a packet received from server!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//拆包方法
		private void parse(DatagramPacket dp) {
			ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, dp
					.getLength());
			DataInputStream dis = new DataInputStream(bais);
			int msgType = 0;
			try {
				msgType = dis.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Msg msg = null;
			//判断传输数据包的类型
			switch (msgType) {
				//tank的信息
			case Msg.TANK_NEW_MSG:
				msg = new TankNewMsg(NetClient.this.tc);//内部类中访问封装类的对象
				//实例化新增坦克信息，让其在新增信息类中完成拆包
				msg.parse(dis);
				break;
				//tank移动的信息
			case Msg.TANK_MOVE_MSG:
				msg = new TankMoveMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
				//子弹的信息
			case Msg.MISSILE_NEW_MSG:
				msg = new MissileNewMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
				//tank死亡的信息
			case Msg.TANK_DEAD_MSG:
				msg = new TankDeadMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
				//子弹死亡的信息
			case Msg.MISSILE_DEAD_MSG:
				msg = new MissileDeadMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
			}

		}

	}
	
	/**
	 * 取得UDP端口(客户端接收数据用)
	 * @return
	 */
	public int getUdpPort() {
		return udpPort;
	}
	
	/**
	 * 设定UDP端口(客户端接收数据用)
	 * @param udpPort
	 */
	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}
}
