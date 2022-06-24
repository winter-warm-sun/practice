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
 * 代表坦克诞生的消息类
 */
public class TankNewMsg implements Msg {
	int msgType = Msg.TANK_NEW_MSG;

	Tank tank;

	TankClient tc;
	
	/**
	 * 根据tank的信息构建消息
	 */
	public TankNewMsg(Tank tank) {
		this.tank = tank;
	}
	
	/**
	 * 根据消息产生的场所构建新的消息
	 */
	public TankNewMsg(TankClient tc) {
		this.tc = tc;
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
			dos.writeInt(msgType);//写入信息的类型
			dos.writeInt(tank.id);
			dos.writeInt(tank.x);//写入tank的横坐标
			dos.writeInt(tank.y);//写入tank的纵坐标
			dos.writeInt(tank.dir.ordinal());//写入tank的方向
			dos.writeBoolean(tank.good);
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

			int x = dis.readInt();//tank的横方向
			int y = dis.readInt();//tank的纵坐标
			Dir dir = Dir.values()[dis.readInt()];//tank的方向
			boolean good = dis.readBoolean();//tank的好坏
			boolean exist = false;
			for (int i = 0; i < tc.tanks.size(); i++) {
				Tank t = tc.tanks.get(i);
				if (t.id == id) {
					exist = true;
					break;
				}
			}

			if (!exist) {
				TankNewMsg tnMsg = new TankNewMsg(tc.myTank);
				tc.nc.send(tnMsg);

				Tank t = new Tank(x, y, good, dir, tc);
				t.id = id;
				tc.tanks.add(t);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
