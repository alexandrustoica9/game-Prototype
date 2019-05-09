package com.tutorial.main.states;

import java.awt.Graphics;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.ui.BasicButton;
import com.tutorial.main.ui.ClickListener;
import com.tutorial.main.ui.UIManager;

public class ChangeKeyState extends State{
	
	private UIManager uiManager;
	
	public ChangeKeyState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager();
		
		uiManager.addObject(new BasicButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 32, 200, 64, "Press a Key !", new ClickListener() {
			@Override
			public void onClick() {
				
			}}));
	}

	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
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
		uiManager.render(g);;
	}
	
	
}
