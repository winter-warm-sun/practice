package Message;

import Game.Dir;
import Game.Tank;
import Main.TankClient;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
/**
 * 代表坦克改变方向的消息类
 */
public class TankMoveMsg implements Msg {
	int msgType = Msg.TANK_MOVE_MSG;
	//tank的坐标
	int x, y;
	//tank的id号
	int id;
	//tank上一步的方向
	Dir ptDir;
	//tank当时的方向
	Dir dir;

	TankClient tc;
	/**
	 * 根据坦克相关属性构建消息
	 * @param id
	 * @param x
	 * @param y
	 * @param dir
	 * @param ptDir
	 */
	public TankMoveMsg(int id, int x, int y, Dir dir, Dir ptDir) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.ptDir = ptDir;
	}
	/**
	 * 根据消息产生的场所构建新的消息
	 * @param tc
	 */
	public TankMoveMsg(TankClient tc) {
		this.tc = tc;
	}
	/**
	 * 分析接收到的消息数据
	 * @param dis 接收到的消息数据的输入流
	 */
	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			if (tc.myTank.id == id) {
				return;
			}
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			Dir ptDir = Dir.values()[dis.readInt()];
			// System.out.println("id:" + id + "-x:" + x + "-y:" + y + "-dir:" +
			// dir + "-good:" + good);
			boolean exist = false;
			for (int i = 0; i < tc.tanks.size(); i++) {
				Tank t = tc.tanks.get(i);
				if (t.id == id) {
					t.x = x;
					t.y = y;
					t.dir = dir;
					t.ptDir = ptDir;
					exist = true;
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 发送相关的消息
	 * @param ds 通过该socket发送数据
	 * @param IP 数据的目标IP
	 * @param udpPort 数据的目标端口
	 */
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal());
			dos.writeInt(ptDir.ordinal());
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length,
					new InetSocketAddress(IP, udpPort));
			ds.send(dp);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
