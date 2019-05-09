package dev.alex.bomberman;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.alex.bomberman.display.Display;
import dev.alex.bomberman.gfx.Assets;
import dev.alex.bomberman.gfx.GameCamera;
import dev.alex.bomberman.input.KeyManager;
import dev.alex.bomberman.input.MouseManager;
import dev.alex.bomberman.states.GameState;
import dev.alex.bomberman.states.MenuState;
import dev.alex.bomberman.states.State;
import dev.alex.bomberman.utils.Utils;

public class Game implements Runnable{

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	private static final int WIDTHMULTIPLY = 3, HEIGHTMULTIPLY = 3;
	
	//States
	private State gameState;
	private State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	 
	private void update() {
		keyManager.update();
		
		if (State.getState() != null) {
			State.getState().update();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear the Screen
		g.clearRect(0, 0, width, height);
		
		//Drawing
		if (State.getState() != null) {
			State.getState().render(g);
		}
		
		//End Drawing
		bs.show();
		g.dispose();
	}
	
	@Override 
	public void run() {
		init();
		
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
				render();
				updates++;
				
				delta -= timePerUpdate;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS: " + updates + "	Bombs:" + handler.getWorld().getEntityManager().getPlayer().getCurrentBombs()
									+ "	  Range:" + handler.getWorld().getEntityManager().getPlayer().getRange());
				updates = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) {
			return;
		}
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public static int getWidthmultiply() {
		return WIDTHMULTIPLY;
	}

	public static int getHeightmultiply() {
		return HEIGHTMULTIPLY;
	}

	public State getGameState() {
		return gameState;
	}

	public State getMenuState() {
		return menuState;
	}

}
