package Message;

import Game.Explode;
import Game.Missile;
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
 * 代表子弹消失的消息类
 */
public class MissileDeadMsg implements Msg {
	int msgType = Msg.MISSILE_DEAD_MSG;

	TankClient tc;

	int tankId;

	int id;
	
	/**
	 * 根据坦克的id和子弹本身的id构建消息
	 * @param tankId 坦克id
	 * @param id 子弹本身的id
	 */
	public MissileDeadMsg(int tankId, int id) {
		this.tankId = tankId;
		this.id = id;
	}
	
	/**
	 * 根据消息产生的场所构建新的消息
	 * @param tc
	 */
	public MissileDeadMsg(TankClient tc) {
		this.tc = tc;
	}
	
	/**
	 * 分析接收到的消息数据
	 * @param dis 接收到的消息数据的输入流
	 */
	public void parse(DataInputStream dis) {
		try {
			int tankId = dis.readInt();
			int id = dis.readInt();

			for (int i = 0; i < tc.missiles.size(); i++) {
				Missile m = tc.missiles.get(i);
				//判断每个子弹是否打中敌方tank
				if (m.tankId == tankId && m.id == id) {
					m.live = false;
					tc.explodes.add(new Explode(m.x, m.y, tc));
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
			dos.writeInt(tankId);
			dos.writeInt(id);
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
