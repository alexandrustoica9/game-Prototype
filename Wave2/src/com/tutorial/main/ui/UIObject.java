package com.tutorial.main.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected float x, y;
	protected boolean hovering;
	
	public UIObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	public void onMouseRelease(MouseEvent e) {
		if (hovering) {
			onClick();
		}
	}
	
}
