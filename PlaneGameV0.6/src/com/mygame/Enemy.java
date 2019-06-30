package com.mygame;

import java.awt.Graphics;
import java.awt.Image;

public class Enemy extends GameObject {
	boolean live = true;
	public Enemy(Image img, double x, double y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = 10;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	public void drawSelf(Graphics g) {
		if(live) {
			
			
			g.drawImage(img, (int)x, (int)y, null);
			
			y += speed;
		}else {
			
		}
	}
}
