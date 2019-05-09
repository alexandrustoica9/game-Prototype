package com.tutorial.main.objects.entities.enemies;

import java.awt.Color;

import com.tutorial.main.Handler;
import com.tutorial.main.objects.ID;
import com.tutorial.main.objects.entities.Entity;

public abstract class Enemy extends Entity {
	
	public static final int DEFAULT_WIDTH = 16,
			 				 DEFAULT_HEIGHT = 16;
	public static final int DEFAULT_VELOCITYX = 5,
			 				 DEFAULT_VELOCITYY = 5;
	protected int damage;
	protected Color enemyColor, trailColor;
	
	public Enemy(Handler handler, float x, float y, Color enemyColor, ID id) {
		super(handler, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, id);
		
		velocityY = DEFAULT_VELOCITYX;
		velocityX = DEFAULT_VELOCITYY;
		
		this.enemyColor = enemyColor;
		this.trailColor = enemyColor;
		
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
