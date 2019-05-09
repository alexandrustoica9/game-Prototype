package com.tutorial.main.states;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.audio.AudioManager;
import com.tutorial.main.ui.BasicButton;
import com.tutorial.main.ui.ClickListener;
import com.tutorial.main.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager();
		
		//Play Button
		uiManager.addObject(new BasicButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 3, 200, 64, "Play", new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getGameState());
			}}));
		
		//Settings Button
		uiManager.addObject(new BasicButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 100, 200, 64, "Settings", new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getSettingsState());
			}}));
		
		//Quit Button
		uiManager.addObject(new BasicButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 3 + 200, 200, 64, "Quit", new ClickListener() {
			@Override
			public void onClick() {
				AudioManager.getSound("click").play();
				
				System.exit(0);
			}}));
		
	}

	@Override
	public void update() {
		uiManager.update();
		
		if(getState().equals(this) && handler.getMouseManager().getUiManager() != uiManager) {
			handler.getMouseManager().setUIManager(uiManager);
		}
	}

	@Override
	public void render(Graphics g) {	
		uiManager.render(g);
	}

	public UIManager getUiManager() {
		return uiManager;
	}
	
	
}
