package Game;

import Main.TankClient;
import Message.MissileNewMsg;
import Message.TankMoveMsg;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 代表坦克的类
 *
 */
public class Tank {
	public int id;//设置tank的id
	public static final int SPEED = 5;//设置tank的速度
	public static final int WIDTH = 40;//设置tank的宽度
	public static final int HEIGHT = 50;//设置tank的高度
	public boolean good;//设置tank的好坏，即我方tank和敌方tank
	public int x;//设置tank的横坐标的位置
	public int y;//设置tank的纵坐标的位置

	private boolean live = true;//设置坦克的寿命
	private static Image[] playerOne=null;
	private static Image[] playerTwo=null;
	//建立哈希表，将对应图片放入哈希表中，然后根据good来选取playerOne或者playerTwo的图片
	private static Map<String, Image> images = new HashMap<>();
	private static Map<String, Image> imagesOne = new HashMap<>();
	private static Map<String, Image> imagesTwo = new HashMap<>();
	/**
	 * playerOne和playerTwo数组分别完成对应图片初始化，按上下左右顺序加载
	 */
	static {
		playerOne=new Image[] {
				Toolkit.getDefaultToolkit().getImage("images/player1/p1tankU.gif"),
				Toolkit.getDefaultToolkit().getImage("images/player1/p1tankD.gif"),
				Toolkit.getDefaultToolkit().getImage("images/player1/p1tankL.gif"),
				Toolkit.getDefaultToolkit().getImage("images/player1/p1tankR.gif")
		};
		imagesOne.put("U",playerOne[0]);
		imagesOne.put("D",playerOne[1]);
		imagesOne.put("L",playerOne[2]);
		imagesOne.put("R",playerOne[3]);
	}

	static {
		playerTwo=new Image[] {
				Toolkit.getDefaultToolkit().getImage("images/player2/p2tankU.gif"),
				Toolkit.getDefaultToolkit().getImage("images/player2/p2tankD.gif"),
				Toolkit.getDefaultToolkit().getImage("images/player2/p2tankL.gif"),
				Toolkit.getDefaultToolkit().getImage("images/player2/p2tankR.gif")
		};
		imagesTwo.put("U",playerTwo[0]);
		imagesTwo.put("D",playerTwo[1]);
		imagesTwo.put("L",playerTwo[2]);
		imagesTwo.put("R",playerTwo[3]);
	}
	TankClient tc;

	boolean bL, bU, bR, bD;

	public Dir dir = Dir.STOP;

	public Dir ptDir = Dir.D;
	
	/**
	 * 根据位置和好坏构建坦克
	 * @param x
	 * @param y
	 * @param good
	 */
	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.good = good;
	}
	
	/**
	 * 根据相关属性构建坦克
	 * @param x
	 * @param y
	 * @param good
	 * @param dir
	 * @param tc 游戏的场所
	 */
	public Tank(int x, int y, boolean good, Dir dir, TankClient tc) {
		this(x, y, good);
		this.dir = dir;
		this.tc = tc;
	}
	
	/**
	 * 画出坦克
	 * @param g 画笔
	 */
	public void draw(Graphics g) {
		if (!live) {
			if (!good) {
				tc.tanks.remove(this);
			}
			return;
		}

		if (good)
			images=imagesOne;
		else
			images=imagesTwo;
		g.setColor(Color.BLACK);//设置背景颜色
		g.setFont(new Font("宋体",Font.BOLD,15));
		g.drawString("id:" + id, x, y-10);
		//判断tank的方向，来选择相应的图片，即发生tank的转向
		switch (ptDir) {
		case L://向左
			g.drawImage(images.get("L"),x,y,null);
			break;
		case U://向上
			g.drawImage(images.get("U"),x,y,null);
			break;
		case R://向右
			g.drawImage(images.get("R"),x,y,null);
			break;
		case D://向下
			g.drawImage(images.get("D"),x,y,null);
			break;
		}

		move();
	}
//编写一个tank的移动方法
	private void move() {
		switch (dir) {
		case L:
			x -= SPEED;
			break;
		case U:
			y -= SPEED;
			break;
		case R:
			x += SPEED;
			break;
		case D:
			y += SPEED;
			break;
		case STOP:
			break;
		}
//判断旧的方向
		if (dir != Dir.STOP) {
			ptDir = dir;
		}
//判断tank出界的问题
		if (x < 0)
			x = 0;
		if (y < 30)
			y = 30;
		if (x + WIDTH > TankClient.GAME_WIDTH)
			x = TankClient.GAME_WIDTH - WIDTH;
		if (y + HEIGHT > TankClient.GAME_HEIGHT)
			y = TankClient.GAME_HEIGHT - HEIGHT;

	}
	
	/**
	 * 键按下的消息处理
	 * @param e 按键事件
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;
		}
		locateDirection();
	}
//判断移动方向的方法
	private void locateDirection() {
		Dir oldDir = this.dir;
		//左移
		if (bL && !bU && !bR && !bD)
			dir = Dir.L;
		//上移
		else if (!bL && bU && !bR && !bD)
			dir = Dir.U;
		//右移
		else if (!bL && !bU && bR && !bD)
			dir = Dir.R;
		//=下移
		else if (!bL && !bU && !bR && bD)
			dir = Dir.D;
		//不按就停止
		else if (!bL && !bU && !bR && !bD)
			dir = Dir.STOP;
		//如果方向不一致，则给其他tank传输方向
		if (dir != oldDir) {
			TankMoveMsg msg = new TankMoveMsg(id, x, y, dir, ptDir);
			tc.nc.send(msg);
		}
	}
	
	/**
	 * 键抬起的消息处理
	 * @param e 抬键消息
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_SPACE:
			fire();
			break;
		case KeyEvent.VK_A:
			bL = false;
			break;
		case KeyEvent.VK_W:
			bU = false;
			break;
		case KeyEvent.VK_D:
			bR = false;
			break;
		case KeyEvent.VK_S:
			bD = false;
			break;
		}
		locateDirection();
	}
   	//编写一个开火的方法
	private Missile fire() {
		//如果tank已经死亡，则不能触发发火的方法
		if (!live)
			return null;
   		//编写子弹在什么地方出现的
		int x = this.x + WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + HEIGHT / 2 - Missile.HEIGHT / 2;
		Missile m = new Missile(id, x, y, this.good, this.ptDir, this.tc);
		tc.missiles.add(m);
		//当子弹发射的时候，给其他tank传输一个子弹的消息
		MissileNewMsg msg = new MissileNewMsg(m);
		tc.nc.send(msg);

		return m;
	}
	
	/**
	 * 取得坦克的外切方形
	 * @return 坦克的外切Rectangle
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * 检测坦克是否还活着
	 * @return
	 */
	public boolean isLive() {
		return live;
	}
	
	/**
	 * 设定坦克的生死状态
	 * @param live
	 */
	public void setLive(boolean live) {
		this.live = live;
	}
}
