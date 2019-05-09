package com.tutorial.main.objects.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.objects.ID;
import com.tutorial.main.objects.entities.enemies.Enemy;
import com.tutorial.main.utils.Util;

public class Player extends Entity{
	
	private Handler handler;
	private float speed;
	private static final int DEFAULT_WIDTH = 32,
							 DEFAULT_HEIGHT = 32;
	private int health;
	public static final int DEFAULT_HEALTH = 100;
	
	public Player(Handler handler, float x, float y, ID id) {
		super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, id);
		this.handler = handler;
		this.health = DEFAULT_HEALTH;
		
		speed = 5f;
	}
	
	@Override
	public void update() {
		getInput();
		
		x = Util.sidesCollision((int) (x + velocityX), 0, Game.WIDTH - width);
		y = Util.sidesCollision((int) (y + velocityY), 0, Game.HEIGHT - height);
		checkCollision();
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white); 
		g.fillRect((int) x, (int) y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public void checkCollision() {
		for (Entity o : handler.getEntityManager().getObjects()) {
			if (o.equals(this)) {
				continue;
			}
			if (getCollisionBounds().intersects(o.getCollisionBounds())) {
				Enemy e = (Enemy) o;
				hurt(e.getDamage());
			}
		}
	}
	
	public void getInput() {
		velocityX = 0;
		velocityY = 0;
		
		if (handler.getKeyInput().up) {
			velocityY = -speed;
		}
		if (handler.getKeyInput().down) {
			velocityY = speed;
		}
		if (handler.getKeyInput().left) {
			velocityX = -speed;
		}
		if (handler.getKeyInput().right) {
			velocityX = speed;
		}
	}
	
	//Getters and Setters
	public int getHealth() {
		return health;
	}

	public void hurt(int amount) {
		health -= amount;
		
		if (health < 0) {
			health = 0;
		}
	}
	
}
