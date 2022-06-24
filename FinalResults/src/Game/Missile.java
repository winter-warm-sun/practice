package Game;

import Main.TankClient;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代表子弹的类
 */
public class Missile {
	/**
	 * 子弹的速度
	 */
	public static final int SPEED = 10;
	/**
	 * 子弹的宽度
	 */
	public static final int WIDTH = 10;
	/**
	 * 子弹的高度
	 */
	public static final int HEIGHT = 10;

	private static Map<String, Image> missileImages = new HashMap<>();
	static Image[] images=new Image[2];
	static {
		//静态代码块，设置子弹的颜色
			images[0]=Toolkit.getDefaultToolkit().getImage("images/missile/missileYellow.gif");
			images[1]=Toolkit.getDefaultToolkit().getImage("images/missile/missileGreen.gif");
			missileImages.put("Y",images[0]);
			missileImages.put("G",images[1]);
	}
	private static int ID = 1;

	TankClient tc;

	public int tankId;

	public int id;

	public int x, y;

	public Dir dir = Dir.R;

	public boolean live = true;

	public boolean good;
	
	/**
	 * 根据位置等属性构造子弹
	 * @param tankId 所属坦克的id号(用于网络版)
	 * @param x 子弹产生的x坐标
	 * @param y 子弹产生的y坐标
	 * @param good 子弹的立场是好还是坏
	 * @param dir 子弹的方向
	 * @see Dir
	 */
	
	public Missile(int tankId, int x, int y, boolean good, Dir dir) {
		this.tankId = tankId;
		this.x = x;
		this.y = y;
		this.good = good;
		this.dir = dir;
		this.id = ID++;
	}
	
	/**
	 * 根据位置和TankClient构建子弹
	 * @param tankId
	 * @param x
	 * @param y
	 * @param good
	 * @param dir
	 * @param tc 子弹构建的场所
	 * @see TankClient
	 */
	public Missile(int tankId, int x, int y, boolean good, Dir dir,
			TankClient tc) {
		this(tankId, x, y, good, dir);
		this.tc = tc;
	}
	
	/**
	 * 画出子弹
	 * @param g 画笔
	 */
	public void draw(Graphics g) {
		//子弹生命结束，移除子弹
		if (!live) {
			tc.missiles.remove(this);
			return;
		}
//判断子弹的好坏，也就是自己坦克发射的子弹和敌方发射的子弹
		if (good)
			g.drawImage(missileImages.get("Y"),x+5,y+5,null);
		else
			g.drawImage(missileImages.get("G"),x+5,y+5,null);
		move();
	}
//子弹移动方法
	private void move() {
		switch (dir) {
		case L://左方向
			x -= SPEED;
			break;
		case U://上方向
			y -= SPEED;
			break;
		case R://右方向
			x += SPEED;
			break;
		case D:///下方向
			y += SPEED;
			break;
			//可以删除
		case STOP://停止的时候
			break;
		}
//判断是否出界
		if (x < 0 || y < 0 || x > TankClient.GAME_WIDTH
				|| y > TankClient.GAME_HEIGHT) {
			live = false;
		}
	}
	
	/**
	 * 取得子弹的外切方形
	 * @return 子弹的外切Rectangle
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * 检测子弹是否撞到坦克
	 * @param t 被检测的坦克
	 * @return 如果撞到返回true,否则返回false
	 */
	public boolean hitTank(Tank t) {
		if (this.live && t.isLive() && this.good != t.good
				&& this.getRect().intersects(t.getRect())) {
			this.live = false;
			t.setLive(false);
			tc.explodes.add(new Explode(x, y, tc));
			return true;
		}
		return false;
	}

}
