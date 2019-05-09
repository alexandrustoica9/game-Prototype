package dev.alex.bomberman.powerups;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.gfx.dieAnimations.DieAnimation;

public class PowerUpManager {

	private Handler handler;
	private ArrayList<PowerUp> powerUps;
	
	public PowerUpManager(Handler handler) {
		this.handler = handler;
		
		powerUps = new ArrayList<>();
	}
	
	public void update() {
		for (int i = 0; i < powerUps.size(); i++) {
			PowerUp p = powerUps.get(i);
			p.update();
			
			if(!p.isActive()) {
				powerUps.remove(p);
			}
		}
	}
	
	public void render(Graphics g) {
		for (PowerUp p : powerUps) {
			p.render(g);
		}
	}
	
	public void addPowerUp(PowerUp p) {
		powerUps.add(p);
	}
	
	public void removePowerUP(PowerUp p) {
		powerUps.remove(p);
	}
}
