package dev.alex.bomberman.powerups;

import java.awt.Graphics;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.gfx.Assets;
import dev.alex.bomberman.tiles.Tile;

public class DecreaseRangePowerUp extends PowerUp{

	public DecreaseRangePowerUp(Handler handler, float x, float y) {
		super(handler, 2, Assets.decreaseRange, x , y);
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
			if (handler.getWorld().getEntityManager().getPlayer().getRange() > 1) {
				handler.getWorld().getEntityManager().getPlayer().modifyRange(-1);
			}
			return true;
		}
		return false;
	}
	
}
