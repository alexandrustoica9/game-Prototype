package dev.alex.bomberman.gfx.dieAnimations;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.entities.Entity;

public class DieAnimationManager {

	private Handler handler;
	private ArrayList<DieAnimation> animList;
	
	public DieAnimationManager(Handler handler) {
		this.handler = handler;
		
		animList = new ArrayList<DieAnimation>();
	}
	
	public void update() {
		for (int i = 0; i < animList.size(); i++) {
			DieAnimation d = animList.get(i);
			d.update();
			
			if(!d.isActive()) {
				animList.remove(d);
			}
		}
	}
	
	public void render(Graphics g) {
		for (DieAnimation d : animList) {
			d.render(g);
		}
	}
	
	public void addDieAnimation(DieAnimation d) {
		animList.add(d);
	}
	
	public void removeDieAnimation(DieAnimation d) {
		animList.remove(d);
	}
	
}
