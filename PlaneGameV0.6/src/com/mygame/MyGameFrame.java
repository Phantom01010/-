package com.mygame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

public class MyGameFrame extends Frame {
	
	Image Reimu = GameUtil.getImage("images/plane.jpg");
	Image Marisa = GameUtil.getImage("images/enemy.jpg");
	Image bg = GameUtil.getImage("images/bg.jpg");
//	Image jiayou = GameUtil.getImage("images/jiayou.gif");
	
	Plane plane = new Plane(Reimu, Constant.GAME_WIDTH/2-(Reimu.getWidth(null)/2), Constant.GAME_HEIGHT-Reimu.getHeight(null));
	Enemy[] enemys = new Enemy[3];
//	Enemy enemy = new Enemy(Reimu, Constant.GAME_WIDTH/2-(Reimu.getWidth(null)/2), 0);
	int count = 0;
	Shell[] shells = {};
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
//		g.drawImage(jiayou, Constant.GAME_WIDTH-200,Constant.GAME_HEIGHT-200, null);
		plane.drawSelf(g);
		shells = plane.getShells();
		for(int i=0; i<shells.length; i++) {
			shells[i].draw(g);
		}
		
		for(int i=0; i<enemys.length; i++) {
			enemys[i].drawSelf(g);
			if(enemys[i].y>Constant.GAME_HEIGHT) {
				enemys[i] = new Enemy(Marisa, Math.random()*Constant.GAME_WIDTH, 0);
			}
			for(int n=0; n<shells.length; n++) {
				//碰撞检测
				boolean peng = shells[n].getRect().intersects(enemys[i].getRect());
				if(peng) {
					enemys[i].live = false;
					count++;
					enemys[i] = new Enemy(Marisa, Math.random()*Constant.GAME_WIDTH, 0);
				}
			}
		}
		Font font = g.getFont();
		Font f = new Font("宋体", Font.TYPE1_FONT,40);
		g.setFont(f);
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawString("得分："+count, 5, Constant.GAME_HEIGHT-25);
		g.drawString("按\"Z\"键发射", Constant.GAME_WIDTH-250, Constant.GAME_HEIGHT-25);
		g.setFont(font);
		g.setColor(c);
	}
	
	class PaintThread extends Thread {
		@Override
		public void run() {
			while(true) {
				repaint();//重画窗口
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
	//键盘监听内部类
	class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
	}
	
	public void launchFrame() {
		this.setTitle("打飞机");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(300,0);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();//启动重画窗口
		addKeyListener(new KeyMonitor());
		
		for(int i=0; i< enemys.length; i++) {
			enemys[i] = new Enemy(Marisa, Math.random()*Constant.GAME_WIDTH, 0);
		}
	}
	
	public static void main (String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
	/*
	 * 双缓冲，解决闪屏问题
	 * 
	 * 
	 * */
	private Image offScreenImage = null;
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
}
