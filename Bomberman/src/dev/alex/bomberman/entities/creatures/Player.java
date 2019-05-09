package dev.alex.bomberman.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.entities.statics.Bomb;
import dev.alex.bomberman.gfx.Animation;
import dev.alex.bomberman.gfx.Assets;
import dev.alex.bomberman.tiles.Tile;

public class Player extends Creature{
	
	Bomb lastBomb;
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	
	//Bomb Timer
	private long lastBombTimer, bombCooldown, bombTimer;
	
	//Inventory
	private int currentBombs, range, maxBombs;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
	
		bounds.x = 12;
		bounds.y = 15;
		bounds.width = 27;
		bounds.height = 30;
		
		bombCooldown = 500;
		bombTimer = bombCooldown;
		currentBombs = 0;
		maxBombs = 1;
		
		range = 1;
		
		//Animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
	}
	
	@Override
	public void update() {
		//Animations
		animDown.update();
		animUp.update();
		animRight.update();
		animLeft.update();
		
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Bombs
		placeBomb();
	}
	
	public void placeBomb() {
		bombTimer += System.currentTimeMillis() - lastBombTimer;
		lastBombTimer = System.currentTimeMillis();
		if (bombTimer < bombCooldown) {
			return;
		}
		
		if (handler.getKeyManager().place_bomb && currentBombs < maxBombs) {
			if (lastBomb == null) {
				lastBomb = new Bomb(handler, Math.round((x - handler.getGameCamera().getxOffset()) / Tile.TILEWIDTH) * Tile.TILEWIDTH, Math.round((y - handler.getGameCamera().getyOffset()) / Tile.TILEHEIGHT) * Tile.TILEHEIGHT);
				handler.getWorld().getEntityManager().addEntity(lastBomb);
				currentBombs++;
			} else if (lastBomb.getBoundsWidth() != 0) {
				lastBomb = new Bomb(handler, Math.round((x - handler.getGameCamera().getxOffset()) / Tile.TILEWIDTH) * Tile.TILEWIDTH, Math.round((y - handler.getGameCamera().getyOffset()) / Tile.TILEHEIGHT) * Tile.TILEHEIGHT);
				handler.getWorld().getEntityManager().addEntity(lastBomb);
				currentBombs++;
			}
		} else {
			return;
		}
		
		bombTimer = 0;
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if (handler.getKeyManager().up) {
			yMove = -speed;
		}
		if (handler.getKeyManager().down) {
			yMove = speed;
		}
		if (handler.getKeyManager().left) {
			xMove = -speed;
		}
		if (handler.getKeyManager().right) {
			xMove = speed;
		}
	}
	
	@Override
	public void die() {
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT, null);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			return Assets.player_stay;
		}
	}
	
	public int getRange() {
		return range;
	}
	
	public void modifyRange(int amount) {
		range += amount;
	}
	
	public int getCurrentBombs() {
		return currentBombs;
	}
	
	public void modifyCurrentBombs(int amount) {
		currentBombs += amount;
	}
	
	public void modifyMaxBombs(int amount) {
		maxBombs += amount;
	}
	
}
