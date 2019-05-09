package com.tutorial.main.objects.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.tutorial.main.Handler;
import com.tutorial.main.objects.ID;

public abstract class Entity {
	
	protected float x, y;
	protected ID id;
	protected float velocityX, velocityY;
	protected Handler handler;
	protected int width, height;
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	//Getters and Setters
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public Rectangle getCollisionBounds() {
		return new Rectangle((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
	}
	
}
