package com.tutorial.main.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

	private ArrayList<UIRectangleObject> objects;
	
	public UIManager() {
		objects = new ArrayList<>();
	}
	
	public void update() {
		for (UIRectangleObject o : objects) {
			o.update();
		}
	}
	
	public void render(Graphics g) {
		for (UIRectangleObject o : objects) {
			o.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		for (UIRectangleObject o : objects) {
			o.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		for (UIRectangleObject o : objects) {
			o.onMouseRelease(e);
		}
	}
	
	public void addObject(UIRectangleObject o) {
		objects.add(o);
	}
	
	public void removeObject(UIRectangleObject o) {
		objects.remove(o);
	}

	public ArrayList<UIRectangleObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIRectangleObject> objects) {
		this.objects = objects;
	}
	
	
}
