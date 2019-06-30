package com.mygame;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends GameObject {
	double degree;
	
	public Shell() {
//		x = 200;
//		y = 200;
//		width = 10;
//		height = 10;
//		speed = 10;
//		
//		degree = -Math.PI/2;
		
	}
	
	public Shell(double x, double y){
		this.x = x;
		this.y = y;
		width = 30;
		height = 30;
		speed = 10;
		
		degree = -Math.PI/2;
	}
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval((int)x, (int)y, width, height);
		x += speed*Math.cos(degree);
		System.out.println("x" + x);
		y += speed*Math.sin(degree);
		System.out.println("y" + y);
		
//		if(x<0||x>Constant.GAME_WIDTH-width) {
//			degree = Math.PI-degree;
//		}
//		
//		if(y<30||y>Constant.GAME_HEIGHT-height) {
//			degree = -degree;
//		}
		
		g.setColor(c);
	}
}
