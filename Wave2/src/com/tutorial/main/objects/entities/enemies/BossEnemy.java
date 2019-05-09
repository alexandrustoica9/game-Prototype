package com.tutorial.main.objects.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.objects.ID;

public class BossEnemy extends Enemy{
	
	public static final int DEFAULT_BOSS_WIDTH = DEFAULT_WIDTH * 6,
							DEFAULT_BOSS_HEIGHT = DEFAULT_HEIGHT * 6;
	
	private boolean attacking = false;
	private Random r;

	public BossEnemy(Handler handler, float x, float y, Color enemyColor, ID id) {
		super(handler, x, y, enemyColor, id);	
		
		velocityX = 0;
		
		width = DEFAULT_BOSS_WIDTH;
		height = DEFAULT_BOSS_HEIGHT;
		
		bounds.width = width;
		bounds.height = height;
		
		r = new Random();
		
		damage = 2;
	}
	
	@Override
	public void update() {
		x += velocityX;
		y += velocityY;
		
		if (y + height > Game.HEIGHT / 3.5) {
			velocityY = 0;
			attacking = true;
		}
		
		if (attacking) {
			if (velocityX == 0) {
				velocityX = 2;
			}
			int spawn = r.nextInt(10);
			if (spawn == 0) {
				handler.getEntityManager().addObject(new BossEnemyBullet(handler, x + width / 2, y + height - 20, Color.red, ID.BasicEnemy));
			}
		}
		
		if (x <= 0 || x >= Game.WIDTH - DEFAULT_BOSS_WIDTH) {
			velocityX *= -1;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(enemyColor);
		g.fillRect((int) x, (int) y, DEFAULT_BOSS_WIDTH, DEFAULT_BOSS_HEIGHT);
	}
	
}
