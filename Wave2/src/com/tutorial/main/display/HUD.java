package com.tutorial.main.display;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.main.Handler;

public class HUD {

	private Handler handler;
	private int greenValue;
	
	private int score;
	private int level;
	
	public HUD(Handler handler) {
		this.handler = handler;
		
		greenValue = handler.getEntityManager().getPlayer().getHealth() * 2;
		score = 0;
		level = 1;
	}
	
	public void update() {
		greenValue = handler.getEntityManager().getPlayer().getHealth() * 2;
		score++;
	}
	
	public void render(Graphics g) {
		//Health bar
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, handler.getEntityManager().getPlayer().getHealth() * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		//Score
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	}
	
}
