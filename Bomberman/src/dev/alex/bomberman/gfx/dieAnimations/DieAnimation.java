package dev.alex.bomberman.gfx.dieAnimations;

import java.awt.Graphics;

import dev.alex.bomberman.gfx.Animation;

public abstract class DieAnimation {
	
	protected float x, y;
	protected boolean active = true;
	protected int speed;
	
	public DieAnimation(float x, float y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public boolean isActive() {
		return active;
	}
	
	public abstract void stop();
	
}
