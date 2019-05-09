package com.tutorial.main.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIRectangleObject extends UIObject{
 
	protected int width, height;
	protected Rectangle bounds;

	public UIRectangleObject(float x, float y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle((int) x, (int) y, width, height);
	}
	
	public void onMouseMove(MouseEvent e) {
		if (bounds.contains(e.getX(), e.getY())) {
			hovering = true;
		} else {
			hovering = false;
		}
	}

	//Getters and Setters
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

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	
}
