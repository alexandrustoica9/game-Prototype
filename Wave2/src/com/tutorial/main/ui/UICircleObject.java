package com.tutorial.main.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public abstract class UICircleObject {
	
	protected float x, y;
	protected int radius;
	protected Ellipse2D bounds;
	protected boolean hovering;
	
	public UICircleObject(float x, float y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		
		bounds = new Ellipse2D.Float(x, y, radius, radius);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	public void onMouseMove(MouseEvent e) {
		if (bounds.contains(e.getX(), e.getY())) {
			hovering = true;
		} else {
			hovering = false;
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		if (hovering) {
			onClick();
		}
	}
	
}
