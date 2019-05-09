package com.tutorial.main.states;

import java.awt.Graphics;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.audio.AudioManager;
import com.tutorial.main.levels.Level;
import com.tutorial.main.ui.BasicButton;
import com.tutorial.main.ui.ClickListener;
import com.tutorial.main.ui.UIManager;

public class GameState extends State{
	
	private Level level;
	private Game game;
	private boolean backgroundMusic;
	private boolean paused;
	private BasicButton pauseButton;
	
	private UIManager uiManager;

	public GameState(Handler handler, Game game) {
		super(handler);
		this.game = game;
		
		level = new Level(game, handler);
		handler.setLevel(level);
		
		uiManager = new UIManager();
	
		uiManager.addObject(new BasicButton(Game.WIDTH - 100, 0, 100, 64, "Menu", new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getMenuState());
			}}));
		
		pauseButton = new BasicButton(Game.WIDTH - 230, 0, 130, 64, "Pause", new ClickListener() {
			@Override
			public void onClick() {
				if (paused) {
					paused = false;
					AudioManager.getMusic("background").play();
				} else {
					paused = true;
					AudioManager.getMusic("background").pause();
				}
			}});
		
		uiManager.addObject(pauseButton);
	}

	@Override
	public void update() {
		if (!paused) {
			level.update();
		}
		
		uiManager.update();
		
		if(handler.getMouseManager().getUiManager() != uiManager) {
			handler.getMouseManager().setUIManager(uiManager);
			level = new Level(game, handler);
			handler.setLevel(level);
		}
		
		if (!backgroundMusic) {
			AudioManager.getMusic("background").loop();
			backgroundMusic = true;
		}
		
		if (paused) {
			pauseButton.setText("Resume");
		} else {
			pauseButton.setText("Pause");
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		level.render(g);
		
		uiManager.render(g);
	}

	public boolean isBackgroundMusic() {
		return backgroundMusic;
	}

	public void setBackgroundMusic(boolean backgroundMusic) {
		this.backgroundMusic = backgroundMusic;
	}
	
}
