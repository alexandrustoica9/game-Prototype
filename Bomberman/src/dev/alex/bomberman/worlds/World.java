package dev.alex.bomberman.worlds;

import java.awt.Graphics;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.entities.EntityManager;
import dev.alex.bomberman.entities.creatures.Player;
import dev.alex.bomberman.gfx.dieAnimations.DieAnimationManager;
import dev.alex.bomberman.powerups.PowerUpManager;
import dev.alex.bomberman.tiles.Tile;
import dev.alex.bomberman.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	//Entities
	private EntityManager entityManager;
	
	//DieAnimations
	private DieAnimationManager dieAnimationManager;
	
	//PowerUps
	private PowerUpManager powerUpManager;
	
	public World(Handler handler, String worldPath) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		dieAnimationManager = new DieAnimationManager(handler);
		powerUpManager = new PowerUpManager(handler);
		
		loadWorld(worldPath);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void update() {
		entityManager.update();
		dieAnimationManager.update();
		powerUpManager.update();
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		//PowerUps
		powerUpManager.render(g);
		
		//Entities
		entityManager.render(g);
		
		//DieAnimations
		dieAnimationManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.dirtTile;
		}
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public DieAnimationManager getDieAnimationManager() {
		return dieAnimationManager;
	}

	public int[][] getTiles() {
		return tiles;
	}

	public PowerUpManager getPowerUpManager() {
		return powerUpManager;
	}
	
}
