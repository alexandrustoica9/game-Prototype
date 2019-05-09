package dev.alex.bomberman.powerups;

import java.awt.Graphics;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.gfx.Assets;
import dev.alex.bomberman.tiles.Tile;

public class IncreaseRangePowerUp extends PowerUp{

	public IncreaseRangePowerUp(Handler handler, float x, float y) {
		super(handler, 1, Assets.increaseRange, x , y);
	}
	
	@Override
	public void update() {
		if (collected()) {
			active = false;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(texture,(int) x,(int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
	}
	
	@Override
	public boolean collected() {
		if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			handler.getWorld().getEntityManager().getPlayer().modifyRange(1);
			return true;
		}
		return false;
	}
	
}
