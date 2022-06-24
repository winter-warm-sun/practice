package Main;

import Game.*;
import Message.MissileDeadMsg;
import Message.TankDeadMsg;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 坦克的游戏场所
 */
public class TankClient extends Frame {
	/**
	 * 游戏场所的宽度
	 */
	public static final int GAME_WIDTH = 800;
	
	/**
	 * 游戏场所的高度
	 */
	public static final int GAME_HEIGHT = 610;

	public Tank myTank = new Tank(50, 100, true, Dir.STOP, this);

	public List<Missile> missiles = new ArrayList<>();

	public List<Explode> explodes = new ArrayList<>();

	public List<Tank> tanks = new ArrayList<>();

	Image offScreenImage = null;

	public NetClient nc = new NetClient(this);

	ConnDialog dialog = new ConnDialog();
	
	@Override
	/**
	 * 重写父类的重画方法
	 */
	public void paint(Graphics g) {
		//取出子弹
		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			//打tank
			if (m.hitTank(myTank)) {
				TankDeadMsg msg = new TankDeadMsg(myTank.id);
				nc.send(msg);//发送tank信息
				MissileDeadMsg mdmMsg = new MissileDeadMsg(m.tankId, m.id);
				nc.send(mdmMsg);//发送子弹的信息
			}
			m.draw(g);
		}
		//爆炸
		for (int i = 0; i < explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		//画tank
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.draw(g);
		}

		myTank.draw(g);

	}

	@Override
	/**
	 * 重写父类的update方法用于实现双缓冲
	 */
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GRAY);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	/**
	 * 窗口的启动方法
	 *
	 */
	public void launchFrame() {
		//标题
		this.setTitle("坦克大战");
		//窗口初始大小
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		//使屏幕居中
		this.setLocationRelativeTo(null);
		//添加关闭事件
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		//用户不能调整大小
		this.setResizable(false);
		//设置背景色
		this.setBackground(Color.gray);
		//添加键盘事件
		this.addKeyListener(new KeyMonitor());
		//使窗口可见
		this.setVisible(true);
		//调用GUI的线程完成重绘
		while (true){
			repaint();
			try {
				//线程休眠  1秒 = 1000毫秒
				Thread.sleep(30);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}


	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		//按下c键可以更改号
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_C) {
				dialog.setVisible(true);
			} else {
				myTank.keyPressed(e);
			}
		}

	}
//设置进入的时候设置端口号
	class ConnDialog extends Dialog {
		JButton b=new JButton("确定");

		TextField tfIP = new TextField("127.0.0.1", 12);

		TextField tfPort = new TextField("" + TankServer.TCP_PORT, 4);

		TextField tfMyUDPPort = new TextField("2223", 4);

		public ConnDialog() {
			super(TankClient.this, true);

			this.setLayout(new FlowLayout());
			this.add(new Label("IP:"));
			this.add(tfIP);
			this.add(new Label("Port:"));
			this.add(tfPort);
			this.add(new Label("My UDP Port:"));
			this.add(tfMyUDPPort);
			this.add(b);
			this.setLocation(750,450);
			this.pack();
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
			});
			b.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String IP = tfIP.getText().trim();
					int port = Integer.parseInt(tfPort.getText().trim());
					int myUDPPort = Integer.parseInt(tfMyUDPPort.getText()
							.trim());
					nc.setUdpPort(myUDPPort);
					nc.connect(IP, port);
					setVisible(false);
				}

			});
		}

	}

}
