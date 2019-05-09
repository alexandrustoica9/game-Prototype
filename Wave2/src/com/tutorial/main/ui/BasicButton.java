package com.tutorial.main.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class BasicButton extends UIRectangleObject{
	
	private ClickListener clicker;
	private String text;
	private int fontSize;
	
	public BasicButton(float x, float y, int width, int height, String text, ClickListener clicker) {
		super(x, y, width, height);
		this.clicker = clicker;
		this.text = text;
		
		fontSize = 30;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, width, height);
		
		Font font = new Font("arial", 1, fontSize);
		g.setFont(font);
		
		Graphics2D g2d = (Graphics2D) g;
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(text, g2d);
		int stringX = (int) x + (width - (int) r.getWidth()) / 2;
		int stringY = (int) y + (height - (int) r.getHeight()) / 2 + fm.getAscent();
		
		g.drawString(text, (int) stringX, (int) stringY); 
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
