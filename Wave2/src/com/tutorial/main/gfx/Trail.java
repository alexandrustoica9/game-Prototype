package com.tutorial.main.gfx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.tutorial.main.Handler;
import com.tutorial.main.objects.ID;

public class Trail{
	
	private float alpha;
	private float x, y;
	private Handler handler;
	private ID id;
	private Color color;
	private int width, height;
	float life;
	
	public Trail(Handler handler, float x, float y, int width, int height, float life, Color color, ID id) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.id = id;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		alpha = 1;
	}
	
	public void update() {
		if (alpha > life) {
			alpha -= life;
		} else {
			handler.getTrailManager().removeTrail(this);
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}
	
}
