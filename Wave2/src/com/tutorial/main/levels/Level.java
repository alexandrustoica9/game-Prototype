package com.tutorial.main.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.display.HUD;
import com.tutorial.main.gfx.TrailManager;
import com.tutorial.main.objects.ID;
import com.tutorial.main.objects.entities.EntityManager;
import com.tutorial.main.objects.entities.Player;
import com.tutorial.main.objects.entities.enemies.BasicEnemy;
import com.tutorial.main.objects.entities.enemies.BossEnemy;
import com.tutorial.main.objects.entities.enemies.SmartEnemy;
import com.tutorial.main.states.State;

public class Level {
	
	private Game game;
	private Handler handler;
	private HUD hud;

	private int spawnX, spawnY;
	private int enemies;
	private Random r;
	
	private EntityManager entityManager;
	private TrailManager trailManager;
	
	public Level(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		handler.setLevel(this);
		entityManager = new EntityManager(new Player(handler, Game.WIDTH / 2,
				Game.HEIGHT / 2, ID.Player));
		hud = new HUD(handler);
		trailManager = new TrailManager();
		
		r = new Random();
		
		entityManager.addObject(new BasicEnemy(handler, r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), Color.red, ID.BasicEnemy));
		entityManager.addObject(new SmartEnemy(handler, r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), Color.red, ID.BasicEnemy));
		entityManager.addObject(new BossEnemy(handler, game.WIDTH / 2 - BossEnemy.DEFAULT_BOSS_WIDTH / 2, -BossEnemy.DEFAULT_BOSS_HEIGHT - 20, Color.red, ID.BasicEnemy));
	}
	
	public void update() {
		hud.update();
		entityManager.update();
		trailManager.update();
		
		if (handler.getEntityManager().getPlayer().getHealth() == 0) {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().getGameOverState());
		}
	}
	
	public void render(Graphics g) {
		entityManager.render(g);
		trailManager.render(g);
		hud.render(g);
	}
	
	public void loadLevel(String path) {
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public TrailManager getTrailManager() {
		return trailManager;
	}
	
}
