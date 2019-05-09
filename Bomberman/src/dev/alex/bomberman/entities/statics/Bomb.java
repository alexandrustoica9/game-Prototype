package dev.alex.bomberman.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.gfx.Animation;
import dev.alex.bomberman.gfx.Assets;
import dev.alex.bomberman.gfx.dieAnimations.Explosion;
import dev.alex.bomberman.tiles.Tile;

public class Bomb extends StaticEntity{
	
	private boolean justPlaced;
	private long lastTime, currentTime;
	private int animationSpeed;
	
	//Animations
	private Animation ticking;
	
	public Bomb(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = Tile.TILEWIDTH;
		bounds.height = Tile.TILEHEIGHT;
		justPlaced = true;
		lastTime = System.currentTimeMillis();
		animationSpeed = 1000;
		
		//Animations
		ticking = new Animation(animationSpeed, Assets.bomb);
	}
	
	@Override
	public void update() {
		currentTime += System.currentTimeMillis() - lastTime;
		if (currentTime > animationSpeed * ticking.getLength()) {
			die();
		}
		ticking.update();
		justPlacedCollision();
		lastTime = System.currentTimeMillis();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(ticking.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public void justPlacedCollision() {
		if (justPlaced) {
			if ((new Rectangle((int) x, (int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT)).intersects(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f))) {
				bounds.width = 0;
				bounds.height = 0;
			} else {
				bounds.width = Tile.TILEWIDTH;
				bounds.height = Tile.TILEHEIGHT;
				justPlaced = false;
			}
		}
	}
	
	@Override
	public void die() {
		handler.getWorld().getDieAnimationManager().addDieAnimation(new Explosion(handler, x, y, handler.getWorld().getEntityManager().getPlayer().getRange(), 30));
		active = false;
		handler.getWorld().getEntityManager().getPlayer().modifyCurrentBombs(-1);
	}

}
