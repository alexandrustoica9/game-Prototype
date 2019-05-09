package com.tutorial.main.objects.entities.enemies;

import java.awt.Color;
import java.util.Random;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.gfx.Trail;
import com.tutorial.main.objects.ID;

public class BossEnemyBullet extends BasicEnemy{
	
	Random r = new Random();
	
	public BossEnemyBullet(Handler handler, float x, float y, Color enemyColor, ID id) {
		super(handler, x, y, enemyColor, id);	
		
		damage = 1;
		velocityX = r.nextInt(5 - -5) - 5;
		
		/*if (r.nextBoolean()) {
			velocityY *= -1;
		}*/
	}
	
	@Override
	public void update() {
		x += velocityX;
		y += velocityY;
		
		if (x <= 0 || x >= Game.WIDTH - DEFAULT_WIDTH) {
			velocityX *= -1;
		}
		
		if (y > Game.HEIGHT) {
			die();
		}
		
		//Trail
		handler.getTrailManager().addTrail(new Trail(handler, x, y, width, height, 0.02f, trailColor, ID.Trail));
	}
	
	public void die() {
		handler.getEntityManager().removeObject(this);
	}
}
