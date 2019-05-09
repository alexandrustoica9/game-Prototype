package com.tutorial.main.states;

import java.awt.Graphics;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.audio.AudioManager;
import com.tutorial.main.ui.BasicButton;
import com.tutorial.main.ui.ClickListener;
import com.tutorial.main.ui.UIManager;

public class GameOverState extends State{
	
	private UIManager uiManager;
	
	public GameOverState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager();
		
		uiManager.addObject(new BasicButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 32, 200, 64, "Game Over", new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getMenuState());
			}
		}));
	}

	@Override
	public void update() {
		uiManager.update();
		
		if (handler.getMouseManager().getUiManager() != uiManager) {
			handler.getMouseManager().setUIManager(uiManager);
		}
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
	
}
