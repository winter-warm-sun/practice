package Game;

import Main.TankClient;

import java.awt.*;

/**
 * 代表爆炸的类
 * 使用大小不一的圆进行模拟
 */
public class Explode {
	int x, y;//定义爆炸时侯的坐标

	private boolean  live=true;//设置是否发生了爆炸
	public TankClient tc;
	int explodeCount=0;
	//避免每次生成图片都需要从硬盘获取文件，所以直接设置为静态
	static Image[] images=new Image[8];
	//
	static {
		for (int i = 0; i < 8; i++) {
			images[i]=Toolkit.getDefaultToolkit().getImage("images/explode/explode" +(i + 1)+".gif");
		}
	}


	private static boolean init=false;
	/**
	 * 根据位置产生新的爆炸
	 * @param x 爆炸点的x坐标
	 * @param y 爆炸点的y坐标
	 * @param tc 爆炸所产生的场所
	 * @see TankClient
	 */
	public Explode(int x, int y, TankClient tc) {
		this.x = x-37;
		this.y = y-40;
		this.tc = tc;
	}
	
	/**
	 * 画出爆炸当前的圆
	 * @param g 画笔
	 * @see Graphics
	 */
	public void draw(Graphics g) {
		/*类尚未加载完毕就实行draw方法，可能致使图片无法绘出，
		所以先完成初始化，使得在击中第一个敌人时也能产生爆炸*/
		if(!init) {
			for (int i = 0; i < images.length; i++) {
				g.drawImage(images[i], -100, -100, null);
			}
			init = true;
		}
		if (!live) {
			tc.explodes.remove(this);
			return;
		}

		//在TankClient端会完成重绘线程，通过explodeCount的自增从而展现出爆炸效果
		if(explodeCount<8) {
			g.drawImage(images[explodeCount],x,y,null);
			explodeCount++;
		}
	}
}
