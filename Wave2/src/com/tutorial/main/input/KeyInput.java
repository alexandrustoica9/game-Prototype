package com.tutorial.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.tutorial.main.Handler;
import com.tutorial.main.states.State;
import com.tutorial.main.utils.Util;

public class KeyInput implements KeyListener{
	
	private Handler handler;
	
	private boolean keys[];
	
	//Player game controls
	public boolean up, down, left, right;
	//Integers for keyboard keys
	private int[] controlsKeyCodes; // 0 = up ; 1 = down; 2 = left; 3 = right;
	
	public KeyInput() {
		keys = new boolean[256];
		
		controlsKeyCodes = new int[4];
		
		//Initial Controls
		controlsKeyCodes[0] = KeyEvent.VK_W;
		controlsKeyCodes[1] = KeyEvent.VK_S;
		controlsKeyCodes[2] = KeyEvent.VK_A;
		controlsKeyCodes[3] = KeyEvent.VK_D;
	}
	
	public void update() {
		up = keys[controlsKeyCodes[0]];
		down = keys[controlsKeyCodes[1]];
		left = keys[controlsKeyCodes[2]];
		right = keys[controlsKeyCodes[3]];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
		if (State.getState() == handler.getGame().getChangeKeyState()) {
			for (int i = 0; i < controlsKeyCodes.length; i++) {
				if (handler.getSettingsState().isChangingControls()[i]) {
					if (Util.available(i, e.getKeyCode(), controlsKeyCodes) != -1) {
						//System.out.println(Util.available(controlsKeyCodes[i], controlsKeyCodes));
						controlsKeyCodes[Util.available(i, e.getKeyCode(),controlsKeyCodes)] = controlsKeyCodes[i];
						controlsKeyCodes[i] = e.getKeyCode();
						break;
					} else {
						controlsKeyCodes[i] = e.getKeyCode();
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
		if (State.getState() == handler.getGame().getChangeKeyState()) {
			handler.getMouseManager().setUIManager(null);
			State.setState(handler.getGame().getSettingsState());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public int[] getControlsKeyCodes() {
		return controlsKeyCodes;
	}
	
	public void setControlsKeyCodes(int index, int value) {
		if (index < 0 || index >= controlsKeyCodes.length) {
			return;
		}
		
		controlsKeyCodes[index] = value;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	
}
