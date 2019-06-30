package com.mygame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Plane extends GameObject {
	boolean left, up, right, down, fire;
	
	boolean live = true;
	
	Shell[] shells = {};
	
	public void drawSelf(Graphics g) {
		if(live) {
		
		
			g.drawImage(img, (int)x, (int)y, null);
			
			if(left) {
				if(x>=-80)
				x -= speed;
			}
			if(right) {
				if(x<=Constant.GAME_WIDTH-80)
				x += speed;
			}
			if(up) {
				if(y>=100)
				y -= speed;
			}
			if(down) {
				if(y<=Constant.GAME_HEIGHT-160)
				y += speed;
			}
			if(fire) {
				fire();
			}
		}else {
			
		}
		
	}
	
	public Plane(Image img, double x, double y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = 10;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_Z:
			fire = true;
			break;
		}
	}
	
	public void fire(){
		Shell[] shellsTemp = new Shell[shells.length+1];
		System.arraycopy(shells, 0, shellsTemp, 0, shells.length);
		shellsTemp[shellsTemp.length-1] = new Shell(x+img.getWidth(null)/2-15, y); 
		shells = shellsTemp;
	}
	
	public Shell[] getShells() {
		for(int i=0; i<shells.length; i++) {
			if(shells[i].y < 0) {
				Shell[] shellsTemp = new Shell[shells.length-1];
				for(int j=0; j<i; j++) {
					shellsTemp[j] = shells[j];
				}
				for(int k=i; k<shells.length-1; k++) {
					shellsTemp[k] = shells[k+1];
				}
				shells = shellsTemp;
			}
		}
		return shells;
	}
	
	public void minusDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_Z:
			fire = false;
			break;
		}
	}
}
