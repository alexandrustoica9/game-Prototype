package com.tutorial.main.objects.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import com.tutorial.main.objects.entities.Player;

public class EntityManager {
	
	private ArrayList<Entity> entities;
	private Player player;
	
	public EntityManager(Player player) {
		entities = new ArrayList<>();
		this.player = player;
		addObject(player);
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
	}
	
	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
	}
	
	public void addObject(Entity e) {
		entities.add(e);
	}
	
	public void removeObject(Entity e) {
		entities.remove(e);
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Entity> getObjects() {
		return entities;
	}
	
	
}