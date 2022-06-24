package Message;

import java.io.DataInputStream;
import java.net.DatagramSocket;
/**
 * 代表网络协议的数据接口
 */
public interface Msg {
	/**
	 * 坦克产生的消息
	 */
	int TANK_NEW_MSG = 1;
	
	/**
	 * 坦克移动的消息
	 */
	int TANK_MOVE_MSG = 2;
	
	/**
	 * 子弹产生的消息
	 */
	int MISSILE_NEW_MSG = 3;
	
	/**
	 * 坦克死掉的消息
	 */
	int TANK_DEAD_MSG = 4;
	
	/**
	 * 子弹死掉的消息
	 */
	int MISSILE_DEAD_MSG = 5;
	
	/**
	 * 发送数据
	 * @param ds
	 * @param IP
	 * @param udpPort
	 */
	void send(DatagramSocket ds, String IP, int udpPort);
	
	/**
	 * 接收并处理数据
	 * @param dis
	 */
	void parse(DataInputStream dis);
}
