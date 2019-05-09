package com.tutorial.main.objects.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.gfx.Trail;
import com.tutorial.main.objects.ID;

public class BasicEnemy extends Enemy{

	public BasicEnemy(Handler handler, float x, float y, Color enemyColor, ID id) {
		super(handler, x, y, enemyColor, id);	
		
		damage = 1;
	}
	
	@Override
	public void update() {
		x += velocityX;
		y += velocityY;
		
		if (y <= 0 || y >= Game.HEIGHT - DEFAULT_HEIGHT) { 
			velocityY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - DEFAULT_WIDTH) {
			velocityX *= -1;
		}
		
		//Trail
		handler.getTrailManager().addTrail(new Trail(handler, x, y, width, height, 0.02f, trailColor, ID.Trail));
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(enemyColor);
		g.fillRect((int) x, (int) y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}	
	
}