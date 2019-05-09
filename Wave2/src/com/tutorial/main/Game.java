package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.tutorial.main.assets.Assets;
import com.tutorial.main.audio.AudioManager;
import com.tutorial.main.display.Display;
import com.tutorial.main.input.KeyInput;
import com.tutorial.main.input.MouseManager;
import com.tutorial.main.levels.Level;
import com.tutorial.main.states.ChangeKeyState;
import com.tutorial.main.states.GameOverState;
import com.tutorial.main.states.GameState;
import com.tutorial.main.states.MenuState;
import com.tutorial.main.states.SettingsState;
import com.tutorial.main.states.State;

public class Game implements Runnable{

	private static final long serialVersionUID = 1550691097823471818L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;	
	
	private Display display;
	
	private Handler handler;
	private Level level;
	
	//States
	private State gameState;
	private State menuState;
	private State settingsState;
	private State changeKeyState;
	private State gameOverState;
	
	//Input
	private KeyInput keyInput;
	private MouseManager mouseManager;
	
	//Class
	public Game() {		
		display = new Display("Let's Build a Game!", WIDTH, HEIGHT);
		keyInput = new KeyInput();
		mouseManager = new MouseManager();
		handler = new Handler(this);
		keyInput.setHandler(handler);
	
		init();
		Assets.init();
		AudioManager.init();
		
		menuState = new MenuState(handler);
		gameState = new GameState(handler, this);
		settingsState = new SettingsState(handler);
		changeKeyState = new ChangeKeyState(handler);
		gameOverState = new GameOverState(handler);
		
		handler.setSettingsState((SettingsState) settingsState); 
		handler.setGameState((GameState) gameState);
		
		State.setState(menuState);
	}
	
	public void init() {
		display.getCanvas().addKeyListener(keyInput);
		display.getFrame().addKeyListener(keyInput);
		
		display.getCanvas().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		int fps = 60;
		double timePerUpdate = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int updates = 0;
		
		while (running) {
			now = System.nanoTime();
			delta += now - lastTime;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= timePerUpdate) {
				update();
				updates++;
				if (running) {
					render();
				}
				delta -= timePerUpdate;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS:  " + updates);
				updates = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	private void update() {
		
		if (State.getState() != null) {
			State.getState().update();
		}
		
		if (State.getState() != gameState) {
			AudioManager.getMusic("background").stop();
			handler.getGameState().setBackgroundMusic(false);
		}
		
		keyInput.update();
	}
	
	private void render() {
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//Clear the Screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		//Drawing
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (State.getState() != null) {
			State.getState().render(g);
		}
		
		//End Drawing
		g.dispose();
		bs.show();
	}

	public KeyInput getKeyInput() {
		return keyInput;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public State getGameState() {
		return gameState;
	}

	public State getMenuState() {
		return menuState;
	}

	public State getSettingsState() {
		return settingsState;
	}

	public State getChangeKeyState() {
		return changeKeyState;
	}

	public State getGameOverState() {
		return gameOverState;
	}
	
}
