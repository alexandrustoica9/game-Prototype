package com.tutorial.main.objects.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.gfx.Trail;
import com.tutorial.main.objects.ID;
import com.tutorial.main.objects.entities.Player;

public class SmartEnemy extends Enemy{
	
	private Player target;

	public SmartEnemy(Handler handler, float x, float y, Color color, ID id) {
		super(handler, x, y, color, id);
		
		target = handler.getEntityManager().getPlayer();
		
		damage = 1;
		velocityX = 2;
		velocityY = 2;
	}
	
	@Override
	public void update() {
		float diffX = target.getX() - x;
		float diffY = target.getY() - y;
		float angle = (float) Math.atan2(diffY, diffX);
	
		
		x += velocityX * Math.cos(angle);
		y += velocityY * Math.sin(angle);
		
		if (y <= 0 || y >= Game.HEIGHT - DEFAULT_HEIGHT) { 
			velocityY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - DEFAULT_WIDTH) {
			velocityX *= -1;
		}
		
		handler.getTrailManager().addTrail(new Trail(handler, x, y, width, height, 0.02f, trailColor, ID.Trail));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(enemyColor);
		g.fillRect((int) x, (int) y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
}
