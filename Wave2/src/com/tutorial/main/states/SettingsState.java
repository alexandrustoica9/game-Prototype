package com.tutorial.main.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game;
import com.tutorial.main.Handler;
import com.tutorial.main.assets.Assets;
import com.tutorial.main.audio.AudioManager;
import com.tutorial.main.ui.BasicButton;
import com.tutorial.main.ui.ClickListener;
import com.tutorial.main.ui.UIManager;

public class SettingsState extends State{
	
	private UIManager uiManager;
	
	private String upKey, downKey, leftKey, rightKey;
	
	private BasicButton upButton, downButton, leftButton, rightButton;
	
	private boolean[] changingControls;

	public SettingsState(Handler handler) {
		super(handler);	
		
		uiManager = new UIManager();
		changingControls = new boolean[4];
		
		upKey = KeyEvent.getKeyText(handler.getKeyInput().getControlsKeyCodes()[0]);
		downKey = KeyEvent.getKeyText(handler.getKeyInput().getControlsKeyCodes()[1]);
		leftKey = KeyEvent.getKeyText(handler.getKeyInput().getControlsKeyCodes()[2]);
		rightKey = KeyEvent.getKeyText(handler.getKeyInput().getControlsKeyCodes()[3]);
		
		uiManager.addObject(new BasicButton(0, 0, 100, 64, "Back", new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getMenuState());
			}}));
		
		leftButton = new BasicButton(Game.WIDTH / 4, Game.HEIGHT / 3, 100, 64, leftKey, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				changingControls[2] = true;
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getChangeKeyState());
			}});
		
		uiManager.addObject(leftButton);
		
		rightButton = new BasicButton(Game.WIDTH / 4 * 3 - 100, Game.HEIGHT / 3, 100, 64, rightKey, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				changingControls[3] = true;
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getChangeKeyState());
			}});
		
		uiManager.addObject(rightButton);
		
		upButton = new BasicButton(Game.WIDTH / 2 - 50, Game.HEIGHT / 3 - 64, 100, 64, upKey, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				changingControls[0] = true;
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getChangeKeyState());
			}});
		
		uiManager.addObject(upButton);
		
		downButton = new BasicButton(Game.WIDTH / 2 - 50, Game.HEIGHT / 3 + 64, 100, 64, downKey, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				changingControls[1] = true;
				AudioManager.getSound("click").play();
				State.setState(handler.getGame().getChangeKeyState());
			}});
		
		uiManager.addObject(downButton);
	}
	
	@Override
	public void update() {
		uiManager.update();
		
		for (int i = 0; i < changingControls.length; i++) {
			changingControls[i] = false;
		}
		
		if(getState().equals(this) && handler.getMouseManager().getUiManager() != uiManager) {
			handler.getMouseManager().setUIManager(uiManager);
		}
		
		upKey = KeyEvent.getKeyText(handler.getKeyInput().getControlsKeyCodes()[0]);
		downKey = KeyEvent.getKeyText(handler.getKeyInput().getControlsKeyCodes()[1]);
		leftKey = KeyEvent.getKeyText(handler.getKeyInput().getControlsKeyCodes()[2]);
		rightKey = KeyEvent.getKeyText(handler.getKeyInput().getControlsKeyCodes()[3]);
		
		upButton.setText(upKey);
		downButton.setText(downKey);
		leftButton.setText(leftKey);
		rightButton.setText(rightKey);
	}
	
	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
		g.drawImage(Assets.arrowDown, Game.WIDTH / 2 - Assets.WIDTH, Game.HEIGHT / 3 + 129, Assets.WIDTH * 2, Assets.HEIGHT * 2,null);
		g.drawImage(Assets.arrowUp, Game.WIDTH / 2 - Assets.WIDTH, Game.HEIGHT / 3 - 65 - Assets.WIDTH * 2, Assets.WIDTH * 2, Assets.HEIGHT * 2,null);
		g.drawImage(Assets.arrowLeft, Game.WIDTH / 4 - Assets.WIDTH * 2 - 1, Game.HEIGHT / 3, Assets.WIDTH * 2, Assets.HEIGHT * 2,null);
		g.drawImage(Assets.arrowRight, Game.WIDTH / 4 * 3 + 1, Game.HEIGHT / 3, Assets.WIDTH * 2, Assets.HEIGHT * 2,null);
	}

	public UIManager getUiManager() {
		return uiManager;
	}

	public boolean[] isChangingControls() {
		return changingControls;
	}

	
}
