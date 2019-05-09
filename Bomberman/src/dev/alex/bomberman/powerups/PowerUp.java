package dev.alex.bomberman.powerups;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.tiles.Tile;

public abstract class PowerUp {
	
	protected boolean active = true;
	protected int id;
	protected BufferedImage texture;
	protected float x, y;
	protected Handler handler;
	protected Rectangle bounds;
	private static int numberOfPowerUps = 4;
	
	public PowerUp(Handler handler, int id, BufferedImage texture, float x, float y) {
		this.id = id;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.handler = handler;
		bounds = new Rectangle((int) x, (int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract boolean collected();
	
	public boolean isActive() {
		return active;
	}
	
	public static int getNumberOfPowerUps() {
		return numberOfPowerUps;
	}
	
}
