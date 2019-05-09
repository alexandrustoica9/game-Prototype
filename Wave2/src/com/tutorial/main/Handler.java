package com.tutorial.main;

import com.tutorial.main.gfx.TrailManager;
import com.tutorial.main.input.KeyInput;
import com.tutorial.main.input.MouseManager;
import com.tutorial.main.levels.Level;
import com.tutorial.main.objects.entities.EntityManager;
import com.tutorial.main.states.GameState;
import com.tutorial.main.states.SettingsState;

public class Handler {

	private Game game;
	private Level level;
	
	//States
	private SettingsState settingsState;
	private GameState gameState;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public KeyInput getKeyInput() {
		return game.getKeyInput();
	}
	
	public EntityManager getEntityManager() {
		return level.getEntityManager();
	}
	
	public TrailManager getTrailManager() {
		return level.getTrailManager();
	}
	
	public Game getGame() {
		return game;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public SettingsState getSettingsState() {
		return settingsState;
	}

	public void setSettingsState(SettingsState settingsState) {
		this.settingsState = settingsState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public GameState getGameState() {
		return gameState;
	}
	
	
	
}
