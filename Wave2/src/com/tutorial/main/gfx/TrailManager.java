package com.tutorial.main.gfx;

import java.awt.Graphics;
import java.util.ArrayList;

public class TrailManager {
	
	private ArrayList<Trail> trails;
	
	public TrailManager() {
		trails = new ArrayList<>();
	}
	
	public void update() {
		for (int i = 0; i < trails.size(); i++) {
			Trail t = trails.get(i);
			t.update();
		}
	}
	
	public void render(Graphics g) {
		for (Trail t : trails) {
			t.render(g);
		}
	}
	
	public void addTrail(Trail t) {
		trails.add(t);
	}
	
	public void removeTrail(Trail t) {
		trails.remove(t);
	}
	
}
